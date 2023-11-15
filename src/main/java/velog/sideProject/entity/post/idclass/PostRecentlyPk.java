package velog.sideProject.entity.post.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class PostRecentlyPk {

    @Column(name = "post_id")
    private Long postId;
    @Column(name = "member_id")
    private Long memberId;

}
