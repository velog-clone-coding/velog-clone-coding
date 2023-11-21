package velog.sideProject.entity.drfatpost;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.post.Tag;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "draft_tag")
public class DraftTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "draft_tag_id")
    private Long draftTagId;


    @Column(name = "draft_tag_string", length = 20, nullable = false)
    private String draftTagString;

    @ManyToOne
    @JoinColumn(name = "draft_post_id", nullable = false)
    private DraftPost draftPost;

    @Builder
    public DraftTag(String draftTagString, DraftPost draftPost) {
        this.draftTagString = draftTagString;
        this.draftPost = draftPost;
    }
}
