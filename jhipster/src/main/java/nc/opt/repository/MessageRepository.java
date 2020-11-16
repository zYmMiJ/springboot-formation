package nc.opt.repository;

import nc.opt.domain.Message;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select message from Message message where message.userMessages.login = ?#{principal.username}")
    List<Message> findByUserMessagesIsCurrentUser();
}
