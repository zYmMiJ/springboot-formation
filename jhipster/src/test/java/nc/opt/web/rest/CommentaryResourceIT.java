package nc.opt.web.rest;

import nc.opt.Exercice6App;
import nc.opt.domain.Commentary;
import nc.opt.repository.CommentaryRepository;
import nc.opt.service.CommentaryService;
import nc.opt.service.dto.CommentaryDTO;
import nc.opt.service.mapper.CommentaryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CommentaryResource} REST controller.
 */
@SpringBootTest(classes = Exercice6App.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommentaryResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private CommentaryMapper commentaryMapper;

    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommentaryMockMvc;

    private Commentary commentary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commentary createEntity(EntityManager em) {
        Commentary commentary = new Commentary()
            .text(DEFAULT_TEXT)
            .creationDate(DEFAULT_CREATION_DATE);
        return commentary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commentary createUpdatedEntity(EntityManager em) {
        Commentary commentary = new Commentary()
            .text(UPDATED_TEXT)
            .creationDate(UPDATED_CREATION_DATE);
        return commentary;
    }

    @BeforeEach
    public void initTest() {
        commentary = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommentary() throws Exception {
        int databaseSizeBeforeCreate = commentaryRepository.findAll().size();
        // Create the Commentary
        CommentaryDTO commentaryDTO = commentaryMapper.toDto(commentary);
        restCommentaryMockMvc.perform(post("/api/commentaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentaryDTO)))
            .andExpect(status().isCreated());

        // Validate the Commentary in the database
        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeCreate + 1);
        Commentary testCommentary = commentaryList.get(commentaryList.size() - 1);
        assertThat(testCommentary.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testCommentary.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
    }

    @Test
    @Transactional
    public void createCommentaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentaryRepository.findAll().size();

        // Create the Commentary with an existing ID
        commentary.setId(1L);
        CommentaryDTO commentaryDTO = commentaryMapper.toDto(commentary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentaryMockMvc.perform(post("/api/commentaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commentary in the database
        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = commentaryRepository.findAll().size();
        // set the field null
        commentary.setText(null);

        // Create the Commentary, which fails.
        CommentaryDTO commentaryDTO = commentaryMapper.toDto(commentary);


        restCommentaryMockMvc.perform(post("/api/commentaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentaryDTO)))
            .andExpect(status().isBadRequest());

        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCommentaries() throws Exception {
        // Initialize the database
        commentaryRepository.saveAndFlush(commentary);

        // Get all the commentaryList
        restCommentaryMockMvc.perform(get("/api/commentaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentary.getId().intValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getCommentary() throws Exception {
        // Initialize the database
        commentaryRepository.saveAndFlush(commentary);

        // Get the commentary
        restCommentaryMockMvc.perform(get("/api/commentaries/{id}", commentary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commentary.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCommentary() throws Exception {
        // Get the commentary
        restCommentaryMockMvc.perform(get("/api/commentaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommentary() throws Exception {
        // Initialize the database
        commentaryRepository.saveAndFlush(commentary);

        int databaseSizeBeforeUpdate = commentaryRepository.findAll().size();

        // Update the commentary
        Commentary updatedCommentary = commentaryRepository.findById(commentary.getId()).get();
        // Disconnect from session so that the updates on updatedCommentary are not directly saved in db
        em.detach(updatedCommentary);
        updatedCommentary
            .text(UPDATED_TEXT)
            .creationDate(UPDATED_CREATION_DATE);
        CommentaryDTO commentaryDTO = commentaryMapper.toDto(updatedCommentary);

        restCommentaryMockMvc.perform(put("/api/commentaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentaryDTO)))
            .andExpect(status().isOk());

        // Validate the Commentary in the database
        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeUpdate);
        Commentary testCommentary = commentaryList.get(commentaryList.size() - 1);
        assertThat(testCommentary.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testCommentary.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommentary() throws Exception {
        int databaseSizeBeforeUpdate = commentaryRepository.findAll().size();

        // Create the Commentary
        CommentaryDTO commentaryDTO = commentaryMapper.toDto(commentary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentaryMockMvc.perform(put("/api/commentaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commentary in the database
        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommentary() throws Exception {
        // Initialize the database
        commentaryRepository.saveAndFlush(commentary);

        int databaseSizeBeforeDelete = commentaryRepository.findAll().size();

        // Delete the commentary
        restCommentaryMockMvc.perform(delete("/api/commentaries/{id}", commentary.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Commentary> commentaryList = commentaryRepository.findAll();
        assertThat(commentaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
