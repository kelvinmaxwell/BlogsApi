package app.karimax.creswave.model;

import app.karimax.creswave.dao.CommentDto;
import app.karimax.creswave.utils.CurrentTime;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    //map dto to posts
    public Comment fromDto(CommentDto commentDto, User user, Post post) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setUser(user); // provide user from impl
        comment.setPost(post);// provide post from impl
        comment.setCreated_at(CurrentTime.getTime());

        return comment;
    }

    //map post to dto
    public CommentDto toDto() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(this.id);
        commentDto.setContent(this.content);
        commentDto.setUsername(this.user.getUsername());
        commentDto.setPost_id(this.post.getId());
        commentDto.setCreated_at(this.created_at);

        return commentDto;
    }
}
