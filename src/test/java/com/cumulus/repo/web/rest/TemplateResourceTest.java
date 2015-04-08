package com.cumulus.repo.web.rest;

import com.cumulus.repo.Application;
import com.cumulus.repo.domain.Template;
import com.cumulus.repo.repository.TemplateRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TemplateResource REST controller.
 *
 * @see TemplateResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TemplateResourceTest {

    private static final String DEFAULT_XML = "SAMPLE_TEXT";
    private static final String UPDATED_XML = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_VERSION = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_VERSION = BigDecimal.ONE;

    private static final Boolean DEFAULT_MASTER = false;
    private static final Boolean UPDATED_MASTER = true;
    private static final String DEFAULT_XML_ID = "SAMPLE_TEXT";
    private static final String UPDATED_XML_ID = "UPDATED_TEXT";

    @Inject
    private TemplateRepository templateRepository;

    private MockMvc restTemplateMockMvc;

    private Template template;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TemplateResource templateResource = new TemplateResource();
        ReflectionTestUtils.setField(templateResource, "templateRepository", templateRepository);
        this.restTemplateMockMvc = MockMvcBuilders.standaloneSetup(templateResource).build();
    }

    @Before
    public void initTest() {
        template = new Template();
        template.setXML(DEFAULT_XML);
        template.setVersion(DEFAULT_VERSION);
        template.setMaster(DEFAULT_MASTER);
        template.setXmlId(DEFAULT_XML_ID);
    }

    @Test
    @Transactional
    public void createTemplate() throws Exception {
        // Validate the database is empty
        assertThat(templateRepository.findAll()).hasSize(0);

        // Create the Template
        restTemplateMockMvc.perform(post("/api/templates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(template)))
                .andExpect(status().isCreated());

        // Validate the Template in the database
        List<Template> templates = templateRepository.findAll();
        assertThat(templates).hasSize(1);
        Template testTemplate = templates.iterator().next();
        assertThat(testTemplate.getXML()).isEqualTo(DEFAULT_XML);
        assertThat(testTemplate.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testTemplate.getMaster()).isEqualTo(DEFAULT_MASTER);
        assertThat(testTemplate.getXmlId()).isEqualTo(DEFAULT_XML_ID);
    }

    @Test
    @Transactional
    public void getAllTemplates() throws Exception {
        // Initialize the database
        templateRepository.saveAndFlush(template);

        // Get all the templates
        restTemplateMockMvc.perform(get("/api/templates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(template.getId().intValue()))
                .andExpect(jsonPath("$.[0].XML").value(DEFAULT_XML.toString()))
                .andExpect(jsonPath("$.[0].version").value(DEFAULT_VERSION.intValue()))
                .andExpect(jsonPath("$.[0].master").value(DEFAULT_MASTER.booleanValue()))
                .andExpect(jsonPath("$.[0].xmlid").value(DEFAULT_XML_ID.toString()));
    }

    @Test
    @Transactional
    public void getTemplate() throws Exception {
        // Initialize the database
        templateRepository.saveAndFlush(template);

        // Get the template
        restTemplateMockMvc.perform(get("/api/templates/{id}", template.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(template.getId().intValue()))
            .andExpect(jsonPath("$.XML").value(DEFAULT_XML.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.intValue()))
            .andExpect(jsonPath("$.master").value(DEFAULT_MASTER.booleanValue()))
            .andExpect(jsonPath("$.xmlid").value(DEFAULT_XML_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTemplate() throws Exception {
        // Get the template
        restTemplateMockMvc.perform(get("/api/templates/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplate() throws Exception {
        // Initialize the database
        templateRepository.saveAndFlush(template);

        // Update the template
        template.setXML(UPDATED_XML);
        template.setVersion(UPDATED_VERSION);
        template.setMaster(UPDATED_MASTER);
        template.setXmlId(UPDATED_XML_ID);
        restTemplateMockMvc.perform(put("/api/templates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(template)))
                .andExpect(status().isOk());

        // Validate the Template in the database
        List<Template> templates = templateRepository.findAll();
        assertThat(templates).hasSize(1);
        Template testTemplate = templates.iterator().next();
        assertThat(testTemplate.getXML()).isEqualTo(UPDATED_XML);
        assertThat(testTemplate.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testTemplate.getMaster()).isEqualTo(UPDATED_MASTER);
        assertThat(testTemplate.getXmlId()).isEqualTo(UPDATED_XML_ID);
    }

    @Test
    @Transactional
    public void deleteTemplate() throws Exception {
        // Initialize the database
        templateRepository.saveAndFlush(template);

        // Get the template
        restTemplateMockMvc.perform(delete("/api/templates/{id}", template.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Template> templates = templateRepository.findAll();
        assertThat(templates).hasSize(0);
    }
}
