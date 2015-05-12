package com.cumulus.repo.web.rest;

import com.cumulus.repo.Application;
import com.cumulus.repo.domain.Accreditlab;
import com.cumulus.repo.repository.AccreditlabRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AccreditlabResource REST controller.
 *
 * @see AccreditlabResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AccreditlabResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_URL = "SAMPLE_TEXT";
    private static final String UPDATED_URL = "UPDATED_TEXT";

    @Inject
    private AccreditlabRepository accreditlabRepository;

    private MockMvc restAccreditlabMockMvc;

    private Accreditlab accreditlab;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AccreditlabResource accreditlabResource = new AccreditlabResource();
        ReflectionTestUtils.setField(accreditlabResource, "accreditlabRepository", accreditlabRepository);
        this.restAccreditlabMockMvc = MockMvcBuilders.standaloneSetup(accreditlabResource).build();
    }

    @Before
    public void initTest() {
        accreditlab = new Accreditlab();
        accreditlab.setName(DEFAULT_NAME);
        accreditlab.setUrl(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createAccreditlab() throws Exception {
        // Validate the database is empty
        assertThat(accreditlabRepository.findAll()).hasSize(0);

        // Create the Accreditlab
        restAccreditlabMockMvc.perform(post("/api/accreditlabs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(accreditlab)))
                .andExpect(status().isCreated());

        // Validate the Accreditlab in the database
        List<Accreditlab> accreditlabs = accreditlabRepository.findAll();
        assertThat(accreditlabs).hasSize(1);
        Accreditlab testAccreditlab = accreditlabs.iterator().next();
        assertThat(testAccreditlab.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAccreditlab.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void getAllAccreditlabs() throws Exception {
        // Initialize the database
        accreditlabRepository.saveAndFlush(accreditlab);

        // Get all the accreditlabs
        restAccreditlabMockMvc.perform(get("/api/accreditlabs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(accreditlab.getId().intValue()))
                .andExpect(jsonPath("$.[0].Name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].Url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getAccreditlab() throws Exception {
        // Initialize the database
        accreditlabRepository.saveAndFlush(accreditlab);

        // Get the accreditlab
        restAccreditlabMockMvc.perform(get("/api/accreditlabs/{id}", accreditlab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(accreditlab.getId().intValue()))
            .andExpect(jsonPath("$.Name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.Url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccreditlab() throws Exception {
        // Get the accreditlab
        restAccreditlabMockMvc.perform(get("/api/accreditlabs/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccreditlab() throws Exception {
        // Initialize the database
        accreditlabRepository.saveAndFlush(accreditlab);

        // Update the accreditlab
        accreditlab.setName(UPDATED_NAME);
        accreditlab.setUrl(UPDATED_URL);
        restAccreditlabMockMvc.perform(put("/api/accreditlabs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(accreditlab)))
                .andExpect(status().isOk());

        // Validate the Accreditlab in the database
        List<Accreditlab> accreditlabs = accreditlabRepository.findAll();
        assertThat(accreditlabs).hasSize(1);
        Accreditlab testAccreditlab = accreditlabs.iterator().next();
        assertThat(testAccreditlab.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAccreditlab.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void deleteAccreditlab() throws Exception {
        // Initialize the database
        accreditlabRepository.saveAndFlush(accreditlab);

        // Get the accreditlab
        restAccreditlabMockMvc.perform(delete("/api/accreditlabs/{id}", accreditlab.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Accreditlab> accreditlabs = accreditlabRepository.findAll();
        assertThat(accreditlabs).hasSize(0);
    }
}
