package velog.sideProject.repository.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}