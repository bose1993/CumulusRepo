package com.cumulus.repo.web.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.cumulus.repo.domain.Property;
import com.cumulus.repo.domain.Template;
import com.cumulus.repo.domain.Toc;
import com.cumulus.repo.domain.User;
import com.cumulus.repo.repository.PropertyRepository;
import com.cumulus.repo.repository.TemplateRepository;
import com.cumulus.repo.repository.TocRepository;
import com.cumulus.repo.service.UserService;
import com.cumulus.repo.web.exceptions.PropertyNotFoundExcpetion;
import com.cumulus.repo.web.rest.util.PaginationUtil;
import com.cumulus.repo.xml.template.PropertyType.PropertyPerformance;
import com.cumulus.repo.xml.template.TestCertificationModel;
import com.cumulus.repo.xml.utils.JaxbUnmarshal;

/**
 * REST controller for Template Service.
 */
@RestController
@RequestMapping("/service")
public class TemplateService {

	private final Logger log = LoggerFactory.getLogger(TemplateResource.class);

	@Inject
	private TemplateRepository templateRepository;
	@Inject
	private UserService userService;
	@Inject
	private TocRepository tocRepository;
	@Inject
	private PropertyRepository propertyRepository;

	private Template parseXMLTemplate(String XML) throws JAXBException,
			PropertyNotFoundExcpetion {
		JaxbUnmarshal jx = new JaxbUnmarshal(XML,
				"com.cumulus.repo.xml.template");
		Template template = new Template();
		Object result = jx.getUnmarshalledObject();
		if (result instanceof JAXBElement) {
			@SuppressWarnings("unchecked")
			JAXBElement<TestCertificationModel> obj = (JAXBElement<TestCertificationModel>) result;
			TestCertificationModel t = obj.getValue();
			template.setXML(XML);
			template.setXmlId(t.getCertificationModelTemplateID());
			Toc toc = this.tocRepository.findOneByName(t.getToC()
					.getConcreteToc());
			if (toc == null) {
				toc = new Toc();
				toc.setName(t.getToC().getConcreteToc());
				toc.setDescription(t.getToC().getTocDescription());
				this.tocRepository.save(toc);
			}
			template.setToc(toc);
			Property property = this.propertyRepository.findOneByRules(t
					.getSecurityProperty().getSecurityPropertyDefinition());
			if (property == null) {
				throw new PropertyNotFoundExcpetion("Property "
						+ t.getSecurityProperty()
								.getSecurityPropertyDefinition() + " Not found");
			}
			template.setProperty(property);
			return template;

		} else {
			throw new JAXBException("Error during Marshaling");
		}

	}

	private boolean checkPropertyAttribute(PropertyPerformance pp) {
		return false;

	}

	/**
	 * POST /templates -> Create a new template.
	 */
	@RequestMapping(value = "/templates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> create(@RequestBody String XML)
			throws URISyntaxException {
		log.debug("REST request to create Template by XML : {}", XML);
		Template template = null;

		try {
			template = this.parseXMLTemplate(XML);
			User user = userService.getUserWithAuthorities();
			template.setVersion(new BigDecimal(1.0));
			template.setMaster(true);
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				template.setUser(user);
			}
		} catch (JAXBException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (PropertyNotFoundExcpetion e) {
			return ResponseEntity
					.badRequest()
					.header("Failure",
							"Property Not Found Insert Property First").build();
		}
		if (template.getId() != null) {
			return ResponseEntity
					.badRequest()
					.header("Failure",
							"A new template cannot already have an ID").build();
		}
		templateRepository.save(template);
		return ResponseEntity.created(
				new URI("/service/templates/" + template.getId())).build();
	}

	/**
	 * PUT /templates -> Updates an existing template.
	 */
	@RequestMapping(value = "/templates", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> update(@RequestBody Template template)
			throws URISyntaxException {
		log.debug("REST request to update Template : {}", template);
		User user = userService.getUserWithAuthorities();
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			template.setUser(user);
		}
		if (template.getId() == null) {
			// return create(template);
		}
		templateRepository.save(template);
		return ResponseEntity.ok().build();
	}

	/**
	 * GET /templates -> get all the templates.
	 */
	@RequestMapping(value = "/templates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Template>> getAll(
			@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit)
			throws URISyntaxException {
		Page<Template> page = templateRepository.findAll(PaginationUtil
				.generatePageRequest(offset, limit));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
				page, "/api/templates", offset, limit);
		return new ResponseEntity<List<Template>>(page.getContent(), headers,
				HttpStatus.OK);
	}

	/**
	 * GET /templates/:id -> get the "id" template.
	 */
	@RequestMapping(value = "/templates/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Template> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get Template : {}", id);
		Template template = templateRepository.findOne(id);

		if (template == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		template.setUser(null);
		return new ResponseEntity<>(template, HttpStatus.OK);
	}

	/**
	 * DELETE /templates/:id -> delete the "id" template.
	 */
	@RequestMapping(value = "/templates/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete Template : {}", id);
		templateRepository.delete(id);
	}
}
