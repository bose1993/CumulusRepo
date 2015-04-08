package com.cumulus.repo.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cumulus.repo.domain.PropertyAttribute;
import com.cumulus.repo.repository.PropertyAttributeRepository;
import com.cumulus.repo.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing PropertyAttribute.
 */
@RestController
@RequestMapping("/api")
public class PropertyAttributeResource {

    private final Logger log = LoggerFactory.getLogger(PropertyAttributeResource.class);

    @Inject
    private PropertyAttributeRepository propertyAttributeRepository;

    /**
     * POST  /propertyAttributes -> Create a new propertyAttribute.
     */
    @RequestMapping(value = "/propertyAttributes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody PropertyAttribute propertyAttribute) throws URISyntaxException {
        log.debug("REST request to save PropertyAttribute : {}", propertyAttribute);
        if (propertyAttribute.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new propertyAttribute cannot already have an ID").build();
        }
        propertyAttributeRepository.save(propertyAttribute);
        return ResponseEntity.created(new URI("/api/propertyAttributes/" + propertyAttribute.getId())).build();
    }

    /**
     * PUT  /propertyAttributes -> Updates an existing propertyAttribute.
     */
    @RequestMapping(value = "/propertyAttributes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody PropertyAttribute propertyAttribute) throws URISyntaxException {
        log.debug("REST request to update PropertyAttribute : {}", propertyAttribute);
        if (propertyAttribute.getId() == null) {
            return create(propertyAttribute);
        }
        propertyAttributeRepository.save(propertyAttribute);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /propertyAttributes -> get all the propertyAttributes.
     */
    @RequestMapping(value = "/propertyAttributes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PropertyAttribute>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<PropertyAttribute> page = propertyAttributeRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/propertyAttributes", offset, limit);
        return new ResponseEntity<List<PropertyAttribute>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /propertyAttributes/:id -> get the "id" propertyAttribute.
     */
    @RequestMapping(value = "/propertyAttributes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PropertyAttribute> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get PropertyAttribute : {}", id);
        PropertyAttribute propertyAttribute = propertyAttributeRepository.findOne(id);
        if (propertyAttribute == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(propertyAttribute, HttpStatus.OK);
    }

    /**
     * DELETE  /propertyAttributes/:id -> delete the "id" propertyAttribute.
     */
    @RequestMapping(value = "/propertyAttributes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PropertyAttribute : {}", id);
        propertyAttributeRepository.delete(id);
    }
}
