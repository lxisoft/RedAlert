package com.lxisoft.redalert.web.rest;

import com.lxisoft.redalert.RedAlertApp;

import com.lxisoft.redalert.domain.Action;
import com.lxisoft.redalert.repository.ActionRepository;
import com.lxisoft.redalert.service.ActionService;
import com.lxisoft.redalert.service.dto.ActionDTO;
import com.lxisoft.redalert.service.mapper.ActionMapper;
import com.lxisoft.redalert.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lxisoft.redalert.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.redalert.domain.enumeration.Reaction;
/**
 * Test class for the ActionResource REST controller.
 *
 * @see ActionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedAlertApp.class)
public class ActionResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_TAKEN_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TAKEN_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Reaction DEFAULT_REACTION = Reaction.COMMENT;
    private static final Reaction UPDATED_REACTION = Reaction.REQUEST_TO_CLOSE;

    private static final Boolean DEFAULT_APPROVAL = false;
    private static final Boolean UPDATED_APPROVAL = true;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private ActionService actionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActionMockMvc;

    private Action action;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActionResource actionResource = new ActionResource(actionService);
        this.restActionMockMvc = MockMvcBuilders.standaloneSetup(actionResource)
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
    public static Action createEntity(EntityManager em) {
        Action action = new Action()
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .description(DEFAULT_DESCRIPTION)
            .takenOn(DEFAULT_TAKEN_ON)
            .reaction(DEFAULT_REACTION)
            .approval(DEFAULT_APPROVAL);
        return action;
    }

    @Before
    public void initTest() {
        action = createEntity(em);
    }

    @Test
    @Transactional
    public void createAction() throws Exception {
        int databaseSizeBeforeCreate = actionRepository.findAll().size();

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);
        restActionMockMvc.perform(post("/api/actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actionDTO)))
            .andExpect(status().isCreated());

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll();
        assertThat(actionList).hasSize(databaseSizeBeforeCreate + 1);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAction.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testAction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAction.getTakenOn()).isEqualTo(DEFAULT_TAKEN_ON);
        assertThat(testAction.getReaction()).isEqualTo(DEFAULT_REACTION);
        assertThat(testAction.isApproval()).isEqualTo(DEFAULT_APPROVAL);
    }

    @Test
    @Transactional
    public void createActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actionRepository.findAll().size();

        // Create the Action with an existing ID
        action.setId(1L);
        ActionDTO actionDTO = actionMapper.toDto(action);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActionMockMvc.perform(post("/api/actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll();
        assertThat(actionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllActions() throws Exception {
        // Initialize the database
        actionRepository.saveAndFlush(action);

        // Get all the actionList
        restActionMockMvc.perform(get("/api/actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(action.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].takenOn").value(hasItem(DEFAULT_TAKEN_ON.toString())))
            .andExpect(jsonPath("$.[*].reaction").value(hasItem(DEFAULT_REACTION.toString())))
            .andExpect(jsonPath("$.[*].approval").value(hasItem(DEFAULT_APPROVAL.booleanValue())));
    }

    @Test
    @Transactional
    public void getAction() throws Exception {
        // Initialize the database
        actionRepository.saveAndFlush(action);

        // Get the action
        restActionMockMvc.perform(get("/api/actions/{id}", action.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(action.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.takenOn").value(DEFAULT_TAKEN_ON.toString()))
            .andExpect(jsonPath("$.reaction").value(DEFAULT_REACTION.toString()))
            .andExpect(jsonPath("$.approval").value(DEFAULT_APPROVAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAction() throws Exception {
        // Get the action
        restActionMockMvc.perform(get("/api/actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAction() throws Exception {
        // Initialize the database
        actionRepository.saveAndFlush(action);
        int databaseSizeBeforeUpdate = actionRepository.findAll().size();

        // Update the action
        Action updatedAction = actionRepository.findById(action.getId()).get();
        // Disconnect from session so that the updates on updatedAction are not directly saved in db
        em.detach(updatedAction);
        updatedAction
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .description(UPDATED_DESCRIPTION)
            .takenOn(UPDATED_TAKEN_ON)
            .reaction(UPDATED_REACTION)
            .approval(UPDATED_APPROVAL);
        ActionDTO actionDTO = actionMapper.toDto(updatedAction);

        restActionMockMvc.perform(put("/api/actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actionDTO)))
            .andExpect(status().isOk());

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAction.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testAction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAction.getTakenOn()).isEqualTo(UPDATED_TAKEN_ON);
        assertThat(testAction.getReaction()).isEqualTo(UPDATED_REACTION);
        assertThat(testAction.isApproval()).isEqualTo(UPDATED_APPROVAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().size();

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restActionMockMvc.perform(put("/api/actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actionDTO)))
            .andExpect(status().isCreated());

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAction() throws Exception {
        // Initialize the database
        actionRepository.saveAndFlush(action);
        int databaseSizeBeforeDelete = actionRepository.findAll().size();

        // Get the action
        restActionMockMvc.perform(delete("/api/actions/{id}", action.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Action> actionList = actionRepository.findAll();
        assertThat(actionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Action.class);
        Action action1 = new Action();
        action1.setId(1L);
        Action action2 = new Action();
        action2.setId(action1.getId());
        assertThat(action1).isEqualTo(action2);
        action2.setId(2L);
        assertThat(action1).isNotEqualTo(action2);
        action1.setId(null);
        assertThat(action1).isNotEqualTo(action2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActionDTO.class);
        ActionDTO actionDTO1 = new ActionDTO();
        actionDTO1.setId(1L);
        ActionDTO actionDTO2 = new ActionDTO();
        assertThat(actionDTO1).isNotEqualTo(actionDTO2);
        actionDTO2.setId(actionDTO1.getId());
        assertThat(actionDTO1).isEqualTo(actionDTO2);
        actionDTO2.setId(2L);
        assertThat(actionDTO1).isNotEqualTo(actionDTO2);
        actionDTO1.setId(null);
        assertThat(actionDTO1).isNotEqualTo(actionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(actionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(actionMapper.fromId(null)).isNull();
    }
}
