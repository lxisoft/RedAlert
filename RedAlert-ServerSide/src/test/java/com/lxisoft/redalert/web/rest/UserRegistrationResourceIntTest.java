package com.lxisoft.redalert.web.rest;

import com.lxisoft.redalert.RedAlertApp;

import com.lxisoft.redalert.domain.UserRegistration;

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
import java.util.List;

import static com.lxisoft.redalert.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.redalert.domain.enumeration.Gender;
import com.lxisoft.redalert.repository.UserRegistrationRepository;
import com.lxisoft.redalert.service.UserRegistrationService;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.service.mapper.UserRegistrationMapper;
import com.lxisoft.redalert.web.rest.errors.ExceptionTranslator;
import com.lxisoft.redalert.domain.enumeration.BloodGroup;
import com.lxisoft.redalert.domain.enumeration.Alert;
/**
 * Test class for the UserRegistrationResource REST controller.
 *
 * @see UserRegistrationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedAlertApp.class)
public class UserRegistrationResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROFILE_PIC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROFILE_PIC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROFILE_PIC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROFILE_PIC_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final BloodGroup DEFAULT_BLOOD_GROUP = BloodGroup.A_POSITIVE;
    private static final BloodGroup UPDATED_BLOOD_GROUP = BloodGroup.A_NEGATIVE;

    private static final Long DEFAULT_CONTACT = 1L;
    private static final Long UPDATED_CONTACT = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final Alert DEFAULT_STATUS = Alert.RED;
    private static final Alert UPDATED_STATUS = Alert.ORANGE;

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Mock
    private UserRegistrationRepository userRegistrationRepositoryMock;

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;
    

    @Mock
    private UserRegistrationService userRegistrationServiceMock;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserRegistrationMockMvc;

    private UserRegistration userRegistration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserRegistrationResource userRegistrationResource = new UserRegistrationResource(userRegistrationService);
        this.restUserRegistrationMockMvc = MockMvcBuilders.standaloneSetup(userRegistrationResource)
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
    public static UserRegistration createEntity(EntityManager em) {
        UserRegistration userRegistration = new UserRegistration()
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .profilePic(DEFAULT_PROFILE_PIC)
            .profilePicContentType(DEFAULT_PROFILE_PIC_CONTENT_TYPE)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .userId(DEFAULT_USER_ID)
            .gender(DEFAULT_GENDER)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .contact(DEFAULT_CONTACT)
            .email(DEFAULT_EMAIL)
            .dob(DEFAULT_DOB)
            .score(DEFAULT_SCORE)
            .status(DEFAULT_STATUS)
            .createdOn(DEFAULT_CREATED_ON);
        return userRegistration;
    }

    @Before
    public void initTest() {
        userRegistration = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserRegistration() throws Exception {
        int databaseSizeBeforeCreate = userRegistrationRepository.findAll().size();

        // Create the UserRegistration
        UserRegistrationDTO userRegistrationDTO = userRegistrationMapper.toDto(userRegistration);
        restUserRegistrationMockMvc.perform(post("/api/user-registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userRegistrationDTO)))
            .andExpect(status().isCreated());

        // Validate the UserRegistration in the database
        List<UserRegistration> userRegistrationList = userRegistrationRepository.findAll();
        assertThat(userRegistrationList).hasSize(databaseSizeBeforeCreate + 1);
        UserRegistration testUserRegistration = userRegistrationList.get(userRegistrationList.size() - 1);
        assertThat(testUserRegistration.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserRegistration.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUserRegistration.getProfilePic()).isEqualTo(DEFAULT_PROFILE_PIC);
        assertThat(testUserRegistration.getProfilePicContentType()).isEqualTo(DEFAULT_PROFILE_PIC_CONTENT_TYPE);
        assertThat(testUserRegistration.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserRegistration.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserRegistration.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserRegistration.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testUserRegistration.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testUserRegistration.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testUserRegistration.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserRegistration.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testUserRegistration.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testUserRegistration.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserRegistration.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    }

    @Test
    @Transactional
    public void createUserRegistrationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userRegistrationRepository.findAll().size();

        // Create the UserRegistration with an existing ID
        userRegistration.setId(1L);
        UserRegistrationDTO userRegistrationDTO = userRegistrationMapper.toDto(userRegistration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserRegistrationMockMvc.perform(post("/api/user-registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userRegistrationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserRegistration in the database
        List<UserRegistration> userRegistrationList = userRegistrationRepository.findAll();
        assertThat(userRegistrationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserRegistrations() throws Exception {
        // Initialize the database
        userRegistrationRepository.saveAndFlush(userRegistration);

        // Get all the userRegistrationList
        restUserRegistrationMockMvc.perform(get("/api/user-registrations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userRegistration.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].profilePicContentType").value(hasItem(DEFAULT_PROFILE_PIC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].profilePic").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROFILE_PIC))))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())));
    }
    
    public void getAllUserRegistrationsWithEagerRelationshipsIsEnabled() throws Exception {
        UserRegistrationResource userRegistrationResource = new UserRegistrationResource(userRegistrationServiceMock);
        when(userRegistrationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restUserRegistrationMockMvc = MockMvcBuilders.standaloneSetup(userRegistrationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restUserRegistrationMockMvc.perform(get("/api/user-registrations?eagerload=true"))
        .andExpect(status().isOk());

        verify(userRegistrationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllUserRegistrationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        UserRegistrationResource userRegistrationResource = new UserRegistrationResource(userRegistrationServiceMock);
            when(userRegistrationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restUserRegistrationMockMvc = MockMvcBuilders.standaloneSetup(userRegistrationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restUserRegistrationMockMvc.perform(get("/api/user-registrations?eagerload=true"))
        .andExpect(status().isOk());

            verify(userRegistrationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getUserRegistration() throws Exception {
        // Initialize the database
        userRegistrationRepository.saveAndFlush(userRegistration);

        // Get the userRegistration
        restUserRegistrationMockMvc.perform(get("/api/user-registrations/{id}", userRegistration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userRegistration.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.profilePicContentType").value(DEFAULT_PROFILE_PIC_CONTENT_TYPE))
            .andExpect(jsonPath("$.profilePic").value(Base64Utils.encodeToString(DEFAULT_PROFILE_PIC)))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserRegistration() throws Exception {
        // Get the userRegistration
        restUserRegistrationMockMvc.perform(get("/api/user-registrations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserRegistration() throws Exception {
        // Initialize the database
        userRegistrationRepository.saveAndFlush(userRegistration);

        int databaseSizeBeforeUpdate = userRegistrationRepository.findAll().size();

        // Update the userRegistration
        UserRegistration updatedUserRegistration = userRegistrationRepository.findById(userRegistration.getId()).get();
        // Disconnect from session so that the updates on updatedUserRegistration are not directly saved in db
        em.detach(updatedUserRegistration);
        updatedUserRegistration
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .profilePic(UPDATED_PROFILE_PIC)
            .profilePicContentType(UPDATED_PROFILE_PIC_CONTENT_TYPE)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .userId(UPDATED_USER_ID)
            .gender(UPDATED_GENDER)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .dob(UPDATED_DOB)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .createdOn(UPDATED_CREATED_ON);
        UserRegistrationDTO userRegistrationDTO = userRegistrationMapper.toDto(updatedUserRegistration);

        restUserRegistrationMockMvc.perform(put("/api/user-registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userRegistrationDTO)))
            .andExpect(status().isOk());

        // Validate the UserRegistration in the database
        List<UserRegistration> userRegistrationList = userRegistrationRepository.findAll();
        assertThat(userRegistrationList).hasSize(databaseSizeBeforeUpdate);
        UserRegistration testUserRegistration = userRegistrationList.get(userRegistrationList.size() - 1);
        assertThat(testUserRegistration.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserRegistration.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserRegistration.getProfilePic()).isEqualTo(UPDATED_PROFILE_PIC);
        assertThat(testUserRegistration.getProfilePicContentType()).isEqualTo(UPDATED_PROFILE_PIC_CONTENT_TYPE);
        assertThat(testUserRegistration.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserRegistration.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserRegistration.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserRegistration.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testUserRegistration.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testUserRegistration.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testUserRegistration.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserRegistration.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testUserRegistration.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testUserRegistration.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserRegistration.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingUserRegistration() throws Exception {
        int databaseSizeBeforeUpdate = userRegistrationRepository.findAll().size();

        // Create the UserRegistration
        UserRegistrationDTO userRegistrationDTO = userRegistrationMapper.toDto(userRegistration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserRegistrationMockMvc.perform(put("/api/user-registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userRegistrationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserRegistration in the database
        List<UserRegistration> userRegistrationList = userRegistrationRepository.findAll();
        assertThat(userRegistrationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserRegistration() throws Exception {
        // Initialize the database
        userRegistrationRepository.saveAndFlush(userRegistration);

        int databaseSizeBeforeDelete = userRegistrationRepository.findAll().size();

        // Get the userRegistration
        restUserRegistrationMockMvc.perform(delete("/api/user-registrations/{id}", userRegistration.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserRegistration> userRegistrationList = userRegistrationRepository.findAll();
        assertThat(userRegistrationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRegistration.class);
        UserRegistration userRegistration1 = new UserRegistration();
        userRegistration1.setId(1L);
        UserRegistration userRegistration2 = new UserRegistration();
        userRegistration2.setId(userRegistration1.getId());
        assertThat(userRegistration1).isEqualTo(userRegistration2);
        userRegistration2.setId(2L);
        assertThat(userRegistration1).isNotEqualTo(userRegistration2);
        userRegistration1.setId(null);
        assertThat(userRegistration1).isNotEqualTo(userRegistration2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRegistrationDTO.class);
        UserRegistrationDTO userRegistrationDTO1 = new UserRegistrationDTO();
        userRegistrationDTO1.setId(1L);
        UserRegistrationDTO userRegistrationDTO2 = new UserRegistrationDTO();
        assertThat(userRegistrationDTO1).isNotEqualTo(userRegistrationDTO2);
        userRegistrationDTO2.setId(userRegistrationDTO1.getId());
        assertThat(userRegistrationDTO1).isEqualTo(userRegistrationDTO2);
        userRegistrationDTO2.setId(2L);
        assertThat(userRegistrationDTO1).isNotEqualTo(userRegistrationDTO2);
        userRegistrationDTO1.setId(null);
        assertThat(userRegistrationDTO1).isNotEqualTo(userRegistrationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userRegistrationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userRegistrationMapper.fromId(null)).isNull();
    }
}
