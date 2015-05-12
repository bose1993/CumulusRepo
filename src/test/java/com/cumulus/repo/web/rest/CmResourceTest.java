package com.cumulus.repo.web.rest;

import com.cumulus.repo.Application;
import com.cumulus.repo.domain.Cm;
import com.cumulus.repo.repository.CmRepository;

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
 * Test class for the CmResource REST controller.
 *
 * @see CmResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CmResourceTest {

    private static final String DEFAULT_XML = "SAMPLE_TEXT";
    private static final String UPDATED_XML = "UPDATED_TEXT";

    private static final Long DEFAULT_CMID = 0L;
    private static final Long UPDATED_CMID = 1L;
    private static final String DEFAULT_XMLID = "SAMPLE_TEXT";
    private static final String UPDATED_XMLID = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_VERSION = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_VERSION = BigDecimal.ONE;

    @Inject
    private CmRepository cmRepository;

    private MockMvc restCmMockMvc;

    private Cm cm;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CmResource cmResource = new CmResource();
        ReflectionTestUtils.setField(cmResource, "cmRepository", cmRepository);
        this.restCmMockMvc = MockMvcBuilders.standaloneSetup(cmResource).build();
    }

    @Before
    public void initTest() {
        cm = new Cm();
        cm.setXml(DEFAULT_XML);
        cm.setCmid(DEFAULT_CMID);
        cm.setXmlid(DEFAULT_XMLID);
        cm.setVersion(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createCm() throws Exception {
        // Validate the database is empty
        assertThat(cmRepository.findAll()).hasSize(0);

        // Create the Cm
        restCmMockMvc.perform(post("/api/cms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cm)))
                .andExpect(status().isCreated());

        // Validate the Cm in the database
        List<Cm> cms = cmRepository.findAll();
        assertThat(cms).hasSize(1);
        Cm testCm = cms.iterator().next();
        assertThat(testCm.getXml()).isEqualTo(DEFAULT_XML);
        assertThat(testCm.getCmid()).isEqualTo(DEFAULT_CMID);
        assertThat(testCm.getXmlid()).isEqualTo(DEFAULT_XMLID);
        assertThat(testCm.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void getAllCms() throws Exception {
        // Initialize the database
        cmRepository.saveAndFlush(cm);

        // Get all the cms
        restCmMockMvc.perform(get("/api/cms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(cm.getId().intValue()))
                .andExpect(jsonPath("$.[0].Xml").value(DEFAULT_XML.toString()))
                .andExpect(jsonPath("$.[0].Cmid").value(DEFAULT_CMID.intValue()))
                .andExpect(jsonPath("$.[0].Xmlid").value(DEFAULT_XMLID.toString()))
                .andExpect(jsonPath("$.[0].Version").value(DEFAULT_VERSION.intValue()));
    }

    @Test
    @Transactional
    public void getCm() throws Exception {
        // Initialize the database
        cmRepository.saveAndFlush(cm);

        // Get the cm
        restCmMockMvc.perform(get("/api/cms/{id}", cm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cm.getId().intValue()))
            .andExpect(jsonPath("$.Xml").value(DEFAULT_XML.toString()))
            .andExpect(jsonPath("$.Cmid").value(DEFAULT_CMID.intValue()))
            .andExpect(jsonPath("$.Xmlid").value(DEFAULT_XMLID.toString()))
            .andExpect(jsonPath("$.Version").value(DEFAULT_VERSION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCm() throws Exception {
        // Get the cm
        restCmMockMvc.perform(get("/api/cms/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCm() throws Exception {
        // Initialize the database
        cmRepository.saveAndFlush(cm);

        // Update the cm
        cm.setXml(UPDATED_XML);
        cm.setCmid(UPDATED_CMID);
        cm.setXmlid(UPDATED_XMLID);
        cm.setVersion(UPDATED_VERSION);
        restCmMockMvc.perform(put("/api/cms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cm)))
                .andExpect(status().isOk());

        // Validate the Cm in the database
        List<Cm> cms = cmRepository.findAll();
        assertThat(cms).hasSize(1);
        Cm testCm = cms.iterator().next();
        assertThat(testCm.getXml()).isEqualTo(UPDATED_XML);
        assertThat(testCm.getCmid()).isEqualTo(UPDATED_CMID);
        assertThat(testCm.getXmlid()).isEqualTo(UPDATED_XMLID);
        assertThat(testCm.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void deleteCm() throws Exception {
        // Initialize the database
        cmRepository.saveAndFlush(cm);

        // Get the cm
        restCmMockMvc.perform(delete("/api/cms/{id}", cm.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Cm> cms = cmRepository.findAll();
        assertThat(cms).hasSize(0);
    }
}
