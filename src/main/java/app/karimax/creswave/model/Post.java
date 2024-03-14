package app.karimax.creswave.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "tbl_posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;
    
    @Column(name = "updated_at")
    private Timestamp updated_at;
}
