package velog.sideProject.entity.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.post.idclass.Post_TagPK;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post_tag")
public class Post_Tag {

    @EmbeddedId
    Post_TagPK postTagPK;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
//    @Column(name = "post_id")
    private Post post;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", nullable = false)
//    @Column(name = "tag_id")
    private Tag tag;

    @Builder
    public Post_Tag(Post_TagPK postTagPK, Post post, Tag tag) {
        this.postTagPK = postTagPK;
        this.post = post;
        this.tag = tag;
    }
}
