package velog.sideProject.entity.drfatpost;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "draftTag")
    private List<DraftPost_DraftTag> draftTagList;

    @Column(name = "draft_tag_string", length = 20, nullable = false)
    private String draftTagString;

    @Builder
    public DraftTag(String draftTagString) {
        this.draftTagString = draftTagString;
    }
}
