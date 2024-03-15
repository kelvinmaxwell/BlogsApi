package app.karimax.creswave.repository;

import app.karimax.creswave.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
