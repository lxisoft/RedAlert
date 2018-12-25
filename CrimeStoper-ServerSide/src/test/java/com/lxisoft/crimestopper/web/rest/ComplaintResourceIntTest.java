package com.lxisoft.crimestopper.web.rest;

import com.lxisoft.crimestopper.CrimeStopperApp;

import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.repository.ComplaintRepository;
import com.lxisoft.crimestopper.repository.search.ComplaintSearchRepository;
import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.mapper.ComplaintMapper;
import com.lxisoft.crimestopper.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.lxisoft.crimestopper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.crimestopper.domain.enumeration.Status;
/**
 * Test class for the ComplaintResource REST controller.
 *
 * @see ComplaintResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrimeStopperApp.class)
public class ComplaintResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TIME_OF_INCIDENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_OF_INCIDENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_MEDIA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_MEDIA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_MEDIA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MEDIA_CONTENT_TYPE = "image/png";

    private static final Status DEFAULT_STATUS = Status.SEEN;
    private static final Status UPDATED_STATUS = Status.ACTION_TAKEN;

    private static final Long DEFAULT_VOTES = 1L;
    private static final Long UPDATED_VOTES = 2L;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Mock
    private ComplaintRepository complaintRepositoryMock;

    @Autowired
    private ComplaintMapper complaintMapper;

    @Mock
    private ComplaintService complaintServiceMock;

    @Autowired
    private ComplaintService complaintService;

    /**
     * This repository is mocked in the com.lxisoft.crimestopper.repository.search test package.
     *
     * @see com.lxisoft.crimestopper.repository.search.ComplaintSearchRepositoryMockConfiguration
     */
    @Autowired
    private ComplaintSearchRepository mockComplaintSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restComplaintMockMvc;

    private Complaint complaint;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComplaintResource complaintResource = new ComplaintResource(complaintService);
        this.restComplaintMockMvc = MockMvcBuilders.standaloneSetup(complaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Complaint createEntity(EntityManager em) {
        Complaint complaint = new Complaint()
            .userId(DEFAULT_USER_ID)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .time(DEFAULT_TIME)
            .timeOfIncident(DEFAULT_TIME_OF_INCIDENT)
            .media(DEFAULT_MEDIA)
            .mediaContentType(DEFAULT_MEDIA_CONTENT_TYPE)
            .status(DEFAULT_STATUS)
            .votes(DEFAULT_VOTES);
        return complaint;
    }

    @Before
    public void initTest() {
        complaint = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplaint() throws Exception {
        int databaseSizeBeforeCreate = complaintRepository.findAll().size();

        // Create the Complaint
        ComplaintDTO complaintDTO = complaintMapper.toDto(complaint);
        restComplaintMockMvc.perform(post("/api/complaints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complaintDTO)))
            .andExpect(status().isCreated());

        // Validate the Complaint in the database
        List<Complaint> complaintList = complaintRepository.findAll();
        assertThat(complaintList).hasSize(databaseSizeBeforeCreate + 1);
        Complaint testComplaint = complaintList.get(complaintList.size() - 1);
        assertThat(testComplaint.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testComplaint.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testComplaint.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testComplaint.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testComplaint.getTimeOfIncident()).isEqualTo(DEFAULT_TIME_OF_INCIDENT);
        assertThat(testComplaint.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testComplaint.getMediaContentType()).isEqualTo(DEFAULT_MEDIA_CONTENT_TYPE);
        assertThat(testComplaint.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testComplaint.getVotes()).isEqualTo(DEFAULT_VOTES);

        // Validate the Complaint in Elasticsearch
        verify(mockComplaintSearchRepository, times(1)).save(testComplaint);
    }

    @Test
    @Transactional
    public void createComplaintWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complaintRepository.findAll().size();

        // Create the Complaint with an existing ID
        complaint.setId(1L);
        ComplaintDTO complaintDTO = complaintMapper.toDto(complaint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplaintMockMvc.perform(post("/api/complaints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complaintDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Complaint in the database
        List<Complaint> complaintList = complaintRepository.findAll();
        assertThat(complaintList).hasSize(databaseSizeBeforeCreate);

        // Validate the Complaint in Elasticsearch
        verify(mockComplaintSearchRepository, times(0)).save(complaint);
    }

    @Test
    @Transactional
    public void getAllComplaints() throws Exception {
        // Initialize the database
        complaintRepository.saveAndFlush(complaint);

        // Get all the complaintList
        restComplaintMockMvc.perform(get("/api/complaints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complaint.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].timeOfIncident").value(hasItem(DEFAULT_TIME_OF_INCIDENT.toString())))
            .andExpect(jsonPath("$.[*].mediaContentType").value(hasItem(DEFAULT_MEDIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].media").value(hasItem(Base64Utils.encodeToString(DEFAULT_MEDIA))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].votes").value(hasItem(DEFAULT_VOTES.intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllComplaintsWithEagerRelationshipsIsEnabled() throws Exception {
        ComplaintResource complaintResource = new ComplaintResource(complaintServiceMock);
        when(complaintServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restComplaintMockMvc = MockMvcBuilders.standaloneSetup(complaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restComplaintMockMvc.perform(get("/api/complaints?eagerload=true"))
        .andExpect(status().isOk());

        verify(complaintServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllComplaintsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ComplaintResource complaintResource = new ComplaintResource(complaintServiceMock);
            when(complaintServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restComplaintMockMvc = MockMvcBuilders.standaloneSetup(complaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restComplaintMockMvc.perform(get("/api/complaints?eagerload=true"))
        .andExpect(status().isOk());

            verify(complaintServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getComplaint() throws Exception {
        // Initialize the database
        complaintRepository.saveAndFlush(complaint);

        // Get the complaint
        restComplaintMockMvc.perform(get("/api/complaints/{id}", complaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(complaint.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.timeOfIncident").value(DEFAULT_TIME_OF_INCIDENT.toString()))
            .andExpect(jsonPath("$.mediaContentType").value(DEFAULT_MEDIA_CONTENT_TYPE))
            .andExpect(jsonPath("$.media").value(Base64Utils.encodeToString(DEFAULT_MEDIA)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.votes").value(DEFAULT_VOTES.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingComplaint() throws Exception {
        // Get the complaint
        restComplaintMockMvc.perform(get("/api/complaints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplaint() throws Exception {
        // Initialize the database
        complaintRepository.saveAndFlush(complaint);

        int databaseSizeBeforeUpdate = complaintRepository.findAll().size();

        // Update the complaint
        Complaint updatedComplaint = complaintRepository.findById(complaint.getId()).get();
        // Disconnect from session so that the updates on updatedComplaint are not directly saved in db
        em.detach(updatedComplaint);
        updatedComplaint
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .time(UPDATED_TIME)
            .timeOfIncident(UPDATED_TIME_OF_INCIDENT)
            .media(UPDATED_MEDIA)
            .mediaContentType(UPDATED_MEDIA_CONTENT_TYPE)
            .status(UPDATED_STATUS)
            .votes(UPDATED_VOTES);
        ComplaintDTO complaintDTO = complaintMapper.toDto(updatedComplaint);

        restComplaintMockMvc.perform(put("/api/complaints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complaintDTO)))
            .andExpect(status().isOk());

        // Validate the Complaint in the database
        List<Complaint> complaintList = complaintRepository.findAll();
        assertThat(complaintList).hasSize(databaseSizeBeforeUpdate);
        Complaint testComplaint = complaintList.get(complaintList.size() - 1);
        assertThat(testComplaint.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testComplaint.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testComplaint.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testComplaint.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testComplaint.getTimeOfIncident()).isEqualTo(UPDATED_TIME_OF_INCIDENT);
        assertThat(testComplaint.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testComplaint.getMediaContentType()).isEqualTo(UPDATED_MEDIA_CONTENT_TYPE);
        assertThat(testComplaint.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testComplaint.getVotes()).isEqualTo(UPDATED_VOTES);

        // Validate the Complaint in Elasticsearch
        verify(mockComplaintSearchRepository, times(1)).save(testComplaint);
    }

    @Test
    @Transactional
    public void updateNonExistingComplaint() throws Exception {
        int databaseSizeBeforeUpdate = complaintRepository.findAll().size();

        // Create the Complaint
        ComplaintDTO complaintDTO = complaintMapper.toDto(complaint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplaintMockMvc.perform(put("/api/complaints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complaintDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Complaint in the database
        List<Complaint> complaintList = complaintRepository.findAll();
        assertThat(complaintList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Complaint in Elasticsearch
        verify(mockComplaintSearchRepository, times(0)).save(complaint);
    }

    @Test
    @Transactional
    public void deleteComplaint() throws Exception {
        // Initialize the database
        complaintRepository.saveAndFlush(complaint);

        int databaseSizeBeforeDelete = complaintRepository.findAll().size();

        // Get the complaint
        restComplaintMockMvc.perform(delete("/api/complaints/{id}", complaint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Complaint> complaintList = complaintRepository.findAll();
        assertThat(complaintList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Complaint in Elasticsearch
        verify(mockComplaintSearchRepository, times(1)).deleteById(complaint.getId());
    }

    @Test
    @Transactional
    public void searchComplaint() throws Exception {
        // Initialize the database
        complaintRepository.saveAndFlush(complaint);
        when(mockComplaintSearchRepository.search(queryStringQuery("id:" + complaint.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(complaint), PageRequest.of(0, 1), 1));
        // Search the complaint
        restComplaintMockMvc.perform(get("/api/_search/complaints?query=id:" + complaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complaint.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].timeOfIncident").value(hasItem(DEFAULT_TIME_OF_INCIDENT.toString())))
            .andExpect(jsonPath("$.[*].mediaContentType").value(hasItem(DEFAULT_MEDIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].media").value(hasItem(Base64Utils.encodeToString(DEFAULT_MEDIA))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].votes").value(hasItem(DEFAULT_VOTES.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Complaint.class);
        Complaint complaint1 = new Complaint();
        complaint1.setId(1L);
        Complaint complaint2 = new Complaint();
        complaint2.setId(complaint1.getId());
        assertThat(complaint1).isEqualTo(complaint2);
        complaint2.setId(2L);
        assertThat(complaint1).isNotEqualTo(complaint2);
        complaint1.setId(null);
        assertThat(complaint1).isNotEqualTo(complaint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplaintDTO.class);
        ComplaintDTO complaintDTO1 = new ComplaintDTO();
        complaintDTO1.setId(1L);
        ComplaintDTO complaintDTO2 = new ComplaintDTO();
        assertThat(complaintDTO1).isNotEqualTo(complaintDTO2);
        complaintDTO2.setId(complaintDTO1.getId());
        assertThat(complaintDTO1).isEqualTo(complaintDTO2);
        complaintDTO2.setId(2L);
        assertThat(complaintDTO1).isNotEqualTo(complaintDTO2);
        complaintDTO1.setId(null);
        assertThat(complaintDTO1).isNotEqualTo(complaintDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(complaintMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(complaintMapper.fromId(null)).isNull();
    }
}
