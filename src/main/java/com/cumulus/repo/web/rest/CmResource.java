package com.cumulus.repo.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cumulus.repo.domain.Cm;
import com.cumulus.repo.repository.CmRepository;
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
 * REST controller for managing Cm.
 */
@RestController
@RequestMapping("/api")
public class CmResource {

    private final Logger log = LoggerFactory.getLogger(CmResource.class);

    @Inject
    private CmRepository cmRepository;

    /**
     * POST  /cms -> Create a new cm.
     */
    @RequestMapping(value = "/cms",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Cm cm) throws URISyntaxException {
        log.debug("REST request to save Cm : {}", cm);
        if (cm.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new cm cannot already have an ID").build();
        }
        cmRepository.save(cm);
        return ResponseEntity.created(new URI("/api/cms/" + cm.getId())).build();
    }

    /**
     * PUT  /cms -> Updates an existing cm.
     */
    @RequestMapping(value = "/cms",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Cm cm) throws URISyntaxException {
        log.debug("REST request to update Cm : {}", cm);
        if (cm.getId() == null) {
            return create(cm);
        }
        cmRepository.save(cm);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /cms -> get all the cms.
     */
    @RequestMapping(value = "/cms",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Cm>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Cm> page = cmRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cms", offset, limit);
        return new ResponseEntity<List<Cm>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cms/:id -> get the "id" cm.
     */
    @RequestMapping(value = "/cms/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Cm> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Cm : {}", id);
        Cm cm = cmRepository.findOne(id);
        if (cm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cm, HttpStatus.OK);
    }

    /**
     * DELETE  /cms/:id -> delete the "id" cm.
     */
    @RequestMapping(value = "/cms/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Cm : {}", id);
        cmRepository.delete(id);
    }
}
