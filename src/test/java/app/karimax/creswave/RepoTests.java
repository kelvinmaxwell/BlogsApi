package app.karimax.creswave;
import app.karimax.creswave.model.Comment;
import app.karimax.creswave.model.Post;
import app.karimax.creswave.model.User;
import app.karimax.creswave.repository.CommentRepository;
import app.karimax.creswave.repository.PostRepository;
import app.karimax.creswave.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepoTests {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        // Create and save a user
        User user = createAndSaveUser();
        // Create and save a post associated with the user
        Post post = createAndSavePost(user);
    }

    @AfterEach
    public void tearDown() {
        // Delete all comments, posts, and users
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    private User createAndSaveUser() {
        User user = MockModelDataFactory.mockUser();
        user = userRepository.save(user);
        return user;
    }

    private Post createAndSavePost(User user) {
        Post post = MockModelDataFactory.mockPost();
        post.setUser(user);
        post = postRepository.save(post);
        return post;
    }

    @Test
    void testConstructor() {
        Comment comment = new Comment();
        assertNotNull(comment);
    }

    @Test
    void testGetterAndSetter() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("testcoment");
        comment.setPost(MockModelDataFactory.mockPost());
        comment.setUser(MockModelDataFactory.mockUser());
        comment.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));

        assertEquals(1L, comment.getId());
        assertEquals("testcoment", comment.getContent());
        assert MockModelDataFactory.mockPost().equals(comment.getPost());
        assert MockModelDataFactory.mockUser().equals(comment.getUser());
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        User user = createAndSaveUser();
        assertNotNull(user.getId()); // Ensure the ID is generated after saving
    }

    @Test
    @Order(2)
    public void testCreatePost() {
        User user = createAndSaveUser();
        Post post = createAndSavePost(user);
        assertNotNull(post.getId()); // Ensure the ID is generated after saving
    }

    @Test
    @Order(3)
    public void testCreateComment() {
        User user = createAndSaveUser();
        Post post = createAndSavePost(user);

        Comment comment = MockModelDataFactory.mockComment();
        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);

        assertNotNull(comment.getId()); // Ensure the ID is generated after saving

        Comment retrievedComment = commentRepository.findById(comment.getId()).get();
        assertNotNull(retrievedComment);
        assertEquals(MockModelDataFactory.mockComment().getContent(), retrievedComment.getContent());
        assert post.equals(retrievedComment.getPost());
        assert user.equals(retrievedComment.getUser());
    }

    @Test
    @Order(4)
    public void testUpdateComment() {
        User user = createAndSaveUser();
        Post post = createAndSavePost(user);

        Comment comment = MockModelDataFactory.mockComment();
        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);
        comment.setContent("new content");

        assertNotNull(comment.getId()); // Ensure the ID is generated after saving

        Comment retrievedComment = commentRepository.findById(comment.getId()).get();
        assertNotNull(retrievedComment);
        assertEquals("new content", retrievedComment.getContent());
        assert post.equals(retrievedComment.getPost());
        assert user.equals(retrievedComment.getUser());
    }

    @Test
    @Order(5)
    @Transactional
    @Commit
    public void testDeleteComment() {
        User user = createAndSaveUser();
        Post post = createAndSavePost(user);

        Comment comment = MockModelDataFactory.mockComment();
        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);

        commentRepository.delete(comment);

        assertFalse(commentRepository.findById(comment.getId()).isPresent());
    }
}
