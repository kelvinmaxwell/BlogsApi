package app.karimax.creswave.dao;

import app.karimax.creswave.model.Post;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private String username;
    private Post post;
    private Timestamp  created_at;
}
