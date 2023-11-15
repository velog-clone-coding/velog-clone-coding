package velog.sideProject.entity.post.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Post_TagPK implements Serializable {

    @Column(name = "post_id")
    private Long postId;
    @Column(name = "tag_id")
    private Long tagId;
}
