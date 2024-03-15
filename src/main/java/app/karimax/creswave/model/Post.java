package app.karimax.creswave.model;

import app.karimax.creswave.dao.PostDto;
import app.karimax.creswave.utils.CurrentTime;
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

    //map dto to posts
    public Post fromDto(PostDto postDto, User user) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user); // provide user from impl
        post.setCreated_at(CurrentTime.getTime());

        return post;
    }

    //map post to dto
    public PostDto toDto() {
        PostDto postDto = new PostDto();
        postDto.setId(this.id);
        postDto.setTitle(this.title);
        postDto.setContent(this.content);
        postDto.setUsername(this.user.getUsername());
        postDto.setCreated_at(this.created_at);

        return postDto;
    }
}
