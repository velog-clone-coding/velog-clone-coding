package velog.sideProject.repository.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByTagString(String tagString);
}

