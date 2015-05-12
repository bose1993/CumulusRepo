package com.cumulus.repo.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.cumulus.repo.domain.Accreditlab;
import com.cumulus.repo.domain.Authority;
import com.cumulus.repo.domain.User;
import com.cumulus.repo.repository.AccreditlabRepository;
import com.cumulus.repo.repository.AuthorityRepository;
import com.cumulus.repo.repository.UserRepository;
import com.cumulus.repo.service.util.RandomUtil;
import com.cumulus.repo.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Accreditlab.
 */
@RestController
@RequestMapping("/api")
public class AccreditlabResource {

	private final Logger log = LoggerFactory
			.getLogger(AccreditlabResource.class);

	@Inject
	private AccreditlabRepository accreditlabRepository;

	@Inject
	private UserRepository userRepository;

	@Inject
	private AuthorityRepository authorityRepository;

	@Inject
	private PasswordEncoder passwordEncoder;

	/**
	 * POST /accreditlabs -> Create a new accreditlab.
	 */
	@RequestMapping(value = "/accreditlabs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Map<String, Object>> create(
			@RequestBody Accreditlab accreditlab) throws URISyntaxException {
		log.debug("REST request to save Accreditlab : {}", accreditlab);
		User u = new User();
		u.setFirstName(accreditlab.getName());
		u.setLastName(accreditlab.getName());
		u.setLogin(RandomUtil.generateActivationKey());
		u.setActivated(true);
		String clearPass = RandomUtil.generateActivationKey();
		String encryptedPassword = passwordEncoder.encode(clearPass);
		u.setPassword(encryptedPassword);
		Authority authority = authorityRepository.findOne("ROLE_AL");
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authority);
		u.setAuthorities(authorities);
		this.userRepository.save(u);
		accreditlab.setuser(u);
		if (accreditlab.getId() != null) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error", "A new accreditlab cannot already have an ID");
			return ResponseEntity
					.badRequest()
					.header("Failure",
							"A new accreditlab cannot already have an ID")
					.body(data);
		}

		accreditlabRepository.save(accreditlab);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("Client_Username", u.getLogin());
		data.put("Client_secret", clearPass);

		return ResponseEntity.created(
				new URI("/api/accreditlabs/" + accreditlab.getId())).body(data);
	}

	/**
	 * PUT /accreditlabs -> Updates an existing accreditlab.
	 */
	@RequestMapping(value = "/accreditlabs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Map<String, Object>> update(
			@RequestBody Accreditlab accreditlab) throws URISyntaxException {
		log.debug("REST request to update Accreditlab : {}", accreditlab);
		// VERIFICARE COSA SUCCEDE CON LA MODIFICA
		if (accreditlab.getId() == null) {
			return create(accreditlab);
		}
		accreditlabRepository.save(accreditlab);
		return ResponseEntity.ok().body(null);
	}

	/**
	 * GET /accreditlabs -> get all the accreditlabs.
	 */
	@RequestMapping(value = "/accreditlabs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Accreditlab>> getAll(
			@RequestParam(value = "page", required = false) Integer offset,
			@RequestParam(value = "per_page", required = false) Integer limit)
			throws URISyntaxException {
		Page<Accreditlab> page = accreditlabRepository.findAll(PaginationUtil
				.generatePageRequest(offset, limit));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
				page, "/api/accreditlabs", offset, limit);
		return new ResponseEntity<List<Accreditlab>>(page.getContent(),
				headers, HttpStatus.OK);
	}

	/**
	 * GET /accreditlabs/:id -> get the "id" accreditlab.
	 */
	@RequestMapping(value = "/accreditlabs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Accreditlab> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get Accreditlab : {}", id);
		Accreditlab accreditlab = accreditlabRepository.findOne(id);
		if (accreditlab == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(accreditlab, HttpStatus.OK);
	}

	/**
	 * DELETE /accreditlabs/:id -> delete the "id" accreditlab.
	 */
	@RequestMapping(value = "/accreditlabs/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete Accreditlab : {}", id);
		accreditlabRepository.delete(id);
	}
}
