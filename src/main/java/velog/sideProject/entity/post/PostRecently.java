package velog.sideProject.entity.post;

import jakarta.persistence.*;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.post.idclass.PostRecentlyPk;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_recently")
public class PostRecently {

    @EmbeddedId
    PostRecentlyPk postTagPK;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
//    @Column(name = "post_id")
    private Post post;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
//    @Column(name = "member_id")
    private Member member;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime cratedAt;

}
