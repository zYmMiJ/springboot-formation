package nc.opt.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link nc.opt.domain.Commentary} entity.
 */
public class CommentaryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 10, max = 50)
    private String text;

    private LocalDate creationDate;


    private Long messageCommentariesId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getMessageCommentariesId() {
        return messageCommentariesId;
    }

    public void setMessageCommentariesId(Long messageId) {
        this.messageCommentariesId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentaryDTO)) {
            return false;
        }

        return id != null && id.equals(((CommentaryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentaryDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", messageCommentariesId=" + getMessageCommentariesId() +
            "}";
    }
}
