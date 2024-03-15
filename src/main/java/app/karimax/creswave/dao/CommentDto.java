package app.karimax.creswave.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "content is required") //validates objects has met set criteria
    @NotBlank(message = "content is required")
    private String content;
    @NotNull(message = "username is required")
    @NotBlank(message = "username is required")
    private String username;
    @NotNull(message = "post id  is required")
    @NotBlank(message = "post id is required")
    private Long post_id;
    private Timestamp created_at;
}
