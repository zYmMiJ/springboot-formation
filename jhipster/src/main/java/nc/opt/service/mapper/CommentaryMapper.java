package nc.opt.service.mapper;


import nc.opt.domain.*;
import nc.opt.service.dto.CommentaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commentary} and its DTO {@link CommentaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface CommentaryMapper extends EntityMapper<CommentaryDTO, Commentary> {

    @Mapping(source = "messageCommentaries.id", target = "messageCommentariesId")
    CommentaryDTO toDto(Commentary commentary);

    @Mapping(source = "messageCommentariesId", target = "messageCommentaries")
    Commentary toEntity(CommentaryDTO commentaryDTO);

    default Commentary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commentary commentary = new Commentary();
        commentary.setId(id);
        return commentary;
    }
}
