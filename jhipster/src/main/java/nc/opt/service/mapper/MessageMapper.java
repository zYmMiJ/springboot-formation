package nc.opt.service.mapper;

import nc.opt.domain.Message;
import nc.opt.service.dto.MessageDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "userMessages.id", target = "userId")
    MessageDTO toDto(Message message);

    @Mapping(source = "userId", target = "user")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
