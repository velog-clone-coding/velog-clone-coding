package velog.sideProject.repository.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.PostLikes;
import velog.sideProject.entity.post.PostRecently;
import velog.sideProject.entity.post.idclass.PostLikesPk;
import velog.sideProject.entity.post.idclass.PostRecentlyPk;

public interface PostRecentlyRepository extends JpaRepository<PostRecently, PostRecentlyPk> {
}
