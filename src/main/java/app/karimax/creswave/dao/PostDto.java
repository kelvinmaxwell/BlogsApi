package app.karimax.creswave.dao;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String user_email;
    private Timestamp created_at;
}
