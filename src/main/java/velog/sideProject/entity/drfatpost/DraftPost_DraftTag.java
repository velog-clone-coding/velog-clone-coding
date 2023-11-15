package velog.sideProject.entity.drfatpost;

import jakarta.persistence.*;
import velog.sideProject.entity.drfatpost.idclass.DraftPost_DraftTagPK;


@Entity
@Table(name = "draft_post_draft_tag")
public class DraftPost_DraftTag {

    @EmbeddedId
    DraftPost_DraftTagPK draftPostDraftTagPK;

    @ManyToOne
    @MapsId("draftTagId")
    @JoinColumn(name = "draft_tag_id", nullable = false)
//    @Column(name = "drfat_tag_id")
    private DraftTag draftTag;

    @ManyToOne
    @MapsId("draftPostId")
    @JoinColumn(name = "draft_post_id", nullable = false)
//    @Column(name = "draft_post_id")
    private DraftPost draftPost;

}
