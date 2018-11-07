package com.lxisoft.redalert.web.rest;

import com.lxisoft.redalert.RedAlertApp;

import com.lxisoft.redalert.domain.LeaderBoard;
import com.lxisoft.redalert.repository.LeaderBoardRepository;
import com.lxisoft.redalert.service.LeaderBoardService;
import com.lxisoft.redalert.service.dto.LeaderBoardDTO;
import com.lxisoft.redalert.service.mapper.LeaderBoardMapper;
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
import java.util.List;


import static com.lxisoft.redalert.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LeaderBoardResource REST controller.
 *
 * @see LeaderBoardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedAlertApp.class)
public class LeaderBoardResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    @Autowired
    private LeaderBoardRepository leaderBoardRepository;

    @Autowired
    private LeaderBoardMapper leaderBoardMapper;
    
    @Autowired
    private LeaderBoardService leaderBoardService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLeaderBoardMockMvc;

    private LeaderBoard leaderBoard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LeaderBoardResource leaderBoardResource = new LeaderBoardResource(leaderBoardService);
        this.restLeaderBoardMockMvc = MockMvcBuilders.standaloneSetup(leaderBoardResource)
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
    public static LeaderBoard createEntity(EntityManager em) {
        LeaderBoard leaderBoard = new LeaderBoard()
            .userName(DEFAULT_USER_NAME)
            .score(DEFAULT_SCORE);
        return leaderBoard;
    }

    @Before
    public void initTest() {
        leaderBoard = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeaderBoard() throws Exception {
        int databaseSizeBeforeCreate = leaderBoardRepository.findAll().size();

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);
        restLeaderBoardMockMvc.perform(post("/api/leader-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO)))
            .andExpect(status().isCreated());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeCreate + 1);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
        assertThat(testLeaderBoard.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testLeaderBoard.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    public void createLeaderBoardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leaderBoardRepository.findAll().size();

        // Create the LeaderBoard with an existing ID
        leaderBoard.setId(1L);
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaderBoardMockMvc.perform(post("/api/leader-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLeaderBoards() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        // Get all the leaderBoardList
        restLeaderBoardMockMvc.perform(get("/api/leader-boards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaderBoard.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }
    
    @Test
    @Transactional
    public void getLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        // Get the leaderBoard
        restLeaderBoardMockMvc.perform(get("/api/leader-boards/{id}", leaderBoard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(leaderBoard.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }

    @Test
    @Transactional
    public void getNonExistingLeaderBoard() throws Exception {
        // Get the leaderBoard
        restLeaderBoardMockMvc.perform(get("/api/leader-boards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();

        // Update the leaderBoard
        LeaderBoard updatedLeaderBoard = leaderBoardRepository.findById(leaderBoard.getId()).get();
        // Disconnect from session so that the updates on updatedLeaderBoard are not directly saved in db
        em.detach(updatedLeaderBoard);
        updatedLeaderBoard
            .userName(UPDATED_USER_NAME)
            .score(UPDATED_SCORE);
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(updatedLeaderBoard);

        restLeaderBoardMockMvc.perform(put("/api/leader-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO)))
            .andExpect(status().isOk());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
        LeaderBoard testLeaderBoard = leaderBoardList.get(leaderBoardList.size() - 1);
        assertThat(testLeaderBoard.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testLeaderBoard.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingLeaderBoard() throws Exception {
        int databaseSizeBeforeUpdate = leaderBoardRepository.findAll().size();

        // Create the LeaderBoard
        LeaderBoardDTO leaderBoardDTO = leaderBoardMapper.toDto(leaderBoard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaderBoardMockMvc.perform(put("/api/leader-boards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leaderBoardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LeaderBoard in the database
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLeaderBoard() throws Exception {
        // Initialize the database
        leaderBoardRepository.saveAndFlush(leaderBoard);

        int databaseSizeBeforeDelete = leaderBoardRepository.findAll().size();

        // Get the leaderBoard
        restLeaderBoardMockMvc.perform(delete("/api/leader-boards/{id}", leaderBoard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();
        assertThat(leaderBoardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoard.class);
        LeaderBoard leaderBoard1 = new LeaderBoard();
        leaderBoard1.setId(1L);
        LeaderBoard leaderBoard2 = new LeaderBoard();
        leaderBoard2.setId(leaderBoard1.getId());
        assertThat(leaderBoard1).isEqualTo(leaderBoard2);
        leaderBoard2.setId(2L);
        assertThat(leaderBoard1).isNotEqualTo(leaderBoard2);
        leaderBoard1.setId(null);
        assertThat(leaderBoard1).isNotEqualTo(leaderBoard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardDTO.class);
        LeaderBoardDTO leaderBoardDTO1 = new LeaderBoardDTO();
        leaderBoardDTO1.setId(1L);
        LeaderBoardDTO leaderBoardDTO2 = new LeaderBoardDTO();
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
        leaderBoardDTO2.setId(leaderBoardDTO1.getId());
        assertThat(leaderBoardDTO1).isEqualTo(leaderBoardDTO2);
        leaderBoardDTO2.setId(2L);
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
        leaderBoardDTO1.setId(null);
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(leaderBoardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(leaderBoardMapper.fromId(null)).isNull();
    }
}
