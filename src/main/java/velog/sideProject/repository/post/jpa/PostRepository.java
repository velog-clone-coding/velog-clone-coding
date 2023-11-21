package velog.sideProject.repository.post.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}