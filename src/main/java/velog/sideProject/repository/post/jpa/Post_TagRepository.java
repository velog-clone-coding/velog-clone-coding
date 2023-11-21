package velog.sideProject.repository.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.Post_Tag;
import velog.sideProject.entity.post.idclass.Post_TagPK;

public interface Post_TagRepository extends JpaRepository<Post_Tag, Post_TagPK> {
}