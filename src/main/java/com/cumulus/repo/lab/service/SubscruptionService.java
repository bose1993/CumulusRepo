package com.cumulus.repo.lab.service;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Cminstance.
 */
@RestController
@RequestMapping("/service")
public class SubscruptionService {

	private final Logger log = LoggerFactory
			.getLogger(SubscruptionService.class);

	/**
	 * POST create a Cminstance from XML
	 * 
	 * @param XML
	 * @return
	 * @throws URISyntaxException
	 */

	@RequestMapping(value = "/cminstances", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@Timed
	public ResponseEntity<Void> create(@RequestBody String XML)
			throws URISyntaxException {
		log.debug("REST request to create Template by XML : {}", XML);

		return ResponseEntity.created(null).build();

	}

}