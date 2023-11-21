package velog.sideProject.repository.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.post.PostLikes;
import velog.sideProject.entity.post.idclass.PostLikesPk;


public interface PostLikesRepository  extends JpaRepository<PostLikes, PostLikesPk> {
}