package com.cumulus.repo.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cumulus.repo.domain.Authority;
import com.cumulus.repo.domain.Template;
import com.cumulus.repo.domain.User;
import com.cumulus.repo.repository.TemplateRepository;
import com.cumulus.repo.service.UserService;
import com.cumulus.repo.web.rest.dto.UserDTO;
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

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Template.
 */
@RestController
@RequestMapping("/api")
public class TemplateResource {

    private final Logger log = LoggerFactory.getLogger(TemplateResource.class);

    @Inject
    private TemplateRepository templateRepository;
    
    @Inject
    private UserService userService;

    /**
     * POST  /templates -> Create a new template.
     */
    @RequestMapping(value = "/templates",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Template template) throws URISyntaxException {
        log.debug("REST request to save Template : {}", template);
        User user = userService.getUserWithAuthorities();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
        	template.setUser(user);
        }
        if (template.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new template cannot already have an ID").build();
        }
        templateRepository.save(template);
        return ResponseEntity.created(new URI("/api/templates/" + template.getId())).build();
    }

    /**
     * PUT  /templates -> Updates an existing template.
     */
    @RequestMapping(value = "/templates",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Template template) throws URISyntaxException {
        log.debug("REST request to update Template : {}", template);
        User user = userService.getUserWithAuthorities();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
        	template.setUser(user);
        }
        if (template.getId() == null) {
            return create(template);
        }
        templateRepository.save(template);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /templates -> get all the templates.
     */
    @RequestMapping(value = "/templates",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Template>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Template> page = templateRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/templates", offset, limit);
        return new ResponseEntity<List<Template>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /templates/:id -> get the "id" template.
     */
    @RequestMapping(value = "/templates/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Template> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Template : {}", id);
        Template template = templateRepository.findOne(id);
        if (template == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    /**
     * DELETE  /templates/:id -> delete the "id" template.
     */
    @RequestMapping(value = "/templates/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Template : {}", id);
        templateRepository.delete(id);
    }
}
