package nc.opt.service;

import nc.opt.domain.Commentary;
import nc.opt.repository.CommentaryRepository;
import nc.opt.service.dto.CommentaryDTO;
import nc.opt.service.mapper.CommentaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Commentary}.
 */
@Service
@Transactional
public class CommentaryService {

    private final Logger log = LoggerFactory.getLogger(CommentaryService.class);

    private final CommentaryRepository commentaryRepository;

    private final CommentaryMapper commentaryMapper;

    public CommentaryService(CommentaryRepository commentaryRepository, CommentaryMapper commentaryMapper) {
        this.commentaryRepository = commentaryRepository;
        this.commentaryMapper = commentaryMapper;
    }

    /**
     * Save a commentary.
     *
     * @param commentaryDTO the entity to save.
     * @return the persisted entity.
     */
    public CommentaryDTO save(CommentaryDTO commentaryDTO) {
        log.debug("Request to save Commentary : {}", commentaryDTO);
        Commentary commentary = commentaryMapper.toEntity(commentaryDTO);
        commentary = commentaryRepository.save(commentary);
        return commentaryMapper.toDto(commentary);
    }

    /**
     * Get all the commentaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommentaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Commentaries");
        return commentaryRepository.findAll(pageable)
            .map(commentaryMapper::toDto);
    }


    /**
     * Get one commentary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommentaryDTO> findOne(Long id) {
        log.debug("Request to get Commentary : {}", id);
        return commentaryRepository.findById(id)
            .map(commentaryMapper::toDto);
    }

    /**
     * Delete the commentary by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Commentary : {}", id);
        commentaryRepository.deleteById(id);
    }
}
