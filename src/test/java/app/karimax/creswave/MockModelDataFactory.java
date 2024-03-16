package app.karimax.creswave;

import app.karimax.creswave.model.Comment;
import app.karimax.creswave.model.Post;
import app.karimax.creswave.model.User;

import java.sql.Timestamp;

public class MockModelDataFactory {
    public static Comment mockComment() {
        Comment comment = new Comment();
        comment.setContent("Mock comment content");
        comment.setPost(mockPost());
        comment.setUser(mockUser());
        comment.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));

        return comment;
    }

    public static Post mockPost() {
        Post post = new Post();
        post.setTitle("Mock post title");
        post.setContent("Mock post content");
        post.setUser(mockUser());
        post.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));

        return post;
    }

    public static User mockUser() {
        User user = new User();
        user.setEmail("mockuser@example.com");
        user.setUsername("mockuser");
        user.setPassword("mockpassword");
        user.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));
        user.setStatus(1);

        return user;
    }
}
