package app.karimax.creswave.dao;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Data Transfer Object attributes
 **/
@Data
public class PostDto {
    private Long id;
    @NotNull(message = "title is required")  //validates objects has met set criteria
    @NotBlank(message = "title is required")
    private String title;
    @NotNull(message = "content is required")
    @NotBlank(message = "content is required")
    private String content;
    @NotNull(message = "username is required")
    @NotBlank(message = "username is required")
    private String username;
    private Timestamp created_at;
}
