package com.cumulus.repo.web.rest;

import com.cumulus.repo.Application;
import com.cumulus.repo.domain.PropertyAttribute;
import com.cumulus.repo.repository.PropertyAttributeRepository;

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
 * Test class for the PropertyAttributeResource REST controller.
 *
 * @see PropertyAttributeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PropertyAttributeResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE = "UPDATED_TEXT";

    private static final Boolean DEFAULT_REQUIRED = false;
    private static final Boolean UPDATED_REQUIRED = true;

    @Inject
    private PropertyAttributeRepository propertyAttributeRepository;

    private MockMvc restPropertyAttributeMockMvc;

    private PropertyAttribute propertyAttribute;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PropertyAttributeResource propertyAttributeResource = new PropertyAttributeResource();
        ReflectionTestUtils.setField(propertyAttributeResource, "propertyAttributeRepository", propertyAttributeRepository);
        this.restPropertyAttributeMockMvc = MockMvcBuilders.standaloneSetup(propertyAttributeResource).build();
    }

    @Before
    public void initTest() {
        propertyAttribute = new PropertyAttribute();
        propertyAttribute.setName(DEFAULT_NAME);
        propertyAttribute.setType(DEFAULT_TYPE);
        propertyAttribute.setRequired(DEFAULT_REQUIRED);
    }

    @Test
    @Transactional
    public void createPropertyAttribute() throws Exception {
        // Validate the database is empty
        assertThat(propertyAttributeRepository.findAll()).hasSize(0);

        // Create the PropertyAttribute
        restPropertyAttributeMockMvc.perform(post("/api/propertyAttributes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(propertyAttribute)))
                .andExpect(status().isCreated());

        // Validate the PropertyAttribute in the database
        List<PropertyAttribute> propertyAttributes = propertyAttributeRepository.findAll();
        assertThat(propertyAttributes).hasSize(1);
        PropertyAttribute testPropertyAttribute = propertyAttributes.iterator().next();
        assertThat(testPropertyAttribute.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPropertyAttribute.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPropertyAttribute.getRequired()).isEqualTo(DEFAULT_REQUIRED);
    }

    @Test
    @Transactional
    public void getAllPropertyAttributes() throws Exception {
        // Initialize the database
        propertyAttributeRepository.saveAndFlush(propertyAttribute);

        // Get all the propertyAttributes
        restPropertyAttributeMockMvc.perform(get("/api/propertyAttributes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(propertyAttribute.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].type").value(DEFAULT_TYPE.toString()))
                .andExpect(jsonPath("$.[0].required").value(DEFAULT_REQUIRED.booleanValue()));
    }

    @Test
    @Transactional
    public void getPropertyAttribute() throws Exception {
        // Initialize the database
        propertyAttributeRepository.saveAndFlush(propertyAttribute);

        // Get the propertyAttribute
        restPropertyAttributeMockMvc.perform(get("/api/propertyAttributes/{id}", propertyAttribute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(propertyAttribute.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.required").value(DEFAULT_REQUIRED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPropertyAttribute() throws Exception {
        // Get the propertyAttribute
        restPropertyAttributeMockMvc.perform(get("/api/propertyAttributes/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropertyAttribute() throws Exception {
        // Initialize the database
        propertyAttributeRepository.saveAndFlush(propertyAttribute);

        // Update the propertyAttribute
        propertyAttribute.setName(UPDATED_NAME);
        propertyAttribute.setType(UPDATED_TYPE);
        propertyAttribute.setRequired(UPDATED_REQUIRED);
        restPropertyAttributeMockMvc.perform(put("/api/propertyAttributes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(propertyAttribute)))
                .andExpect(status().isOk());

        // Validate the PropertyAttribute in the database
        List<PropertyAttribute> propertyAttributes = propertyAttributeRepository.findAll();
        assertThat(propertyAttributes).hasSize(1);
        PropertyAttribute testPropertyAttribute = propertyAttributes.iterator().next();
        assertThat(testPropertyAttribute.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPropertyAttribute.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPropertyAttribute.getRequired()).isEqualTo(UPDATED_REQUIRED);
    }

    @Test
    @Transactional
    public void deletePropertyAttribute() throws Exception {
        // Initialize the database
        propertyAttributeRepository.saveAndFlush(propertyAttribute);

        // Get the propertyAttribute
        restPropertyAttributeMockMvc.perform(delete("/api/propertyAttributes/{id}", propertyAttribute.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PropertyAttribute> propertyAttributes = propertyAttributeRepository.findAll();
        assertThat(propertyAttributes).hasSize(0);
    }
}
