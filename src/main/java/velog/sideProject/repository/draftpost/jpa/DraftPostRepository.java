package velog.sideProject.repository.draftpost.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;

import java.util.List;
import java.util.Optional;

//select draft_tag_string
//        from draft_tag dt
//        join draft_post_draft_tag dpdt
//        on dt.draft_tag_id = dpdt.draft_tag_id
//        join draft_post dp
//        on dpdt.draft_post_id = dp.draft_post_id
//        where dp.draft_post_id = 1;

public interface DraftPostRepository extends JpaRepository<DraftPost, Long> {

    List<DraftPost> findByMember_MemberId(Long userId);

    @Query(value = "select draft_tag_string " +
            "from draft_tag dt " +
            "join draft_post_draft_tag dpdt " +
            "on dt.draft_tag_id = dpdt.draft_tag_id " +
            "join draft_post dp " +
            "on dpdt.draft_post_id = dp.draft_post_id " +
            "where dp.draft_post_id = :postId", nativeQuery = true)
    List<DraftTag> findTagStringsByPostId(@Param("postId") Long postId);

    Optional<DraftPost> findByDraftPostIdAndMember_MemberId(Long draftPostId, Long memberId);
}
