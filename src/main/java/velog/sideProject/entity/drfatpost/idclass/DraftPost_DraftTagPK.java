package velog.sideProject.entity.drfatpost.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class DraftPost_DraftTagPK implements Serializable {

    @Column(name = "draft_tag_id")
    private Long draftTagId;
    @Column(name = "draft_post_id")
    private Long draftPostId;
}
