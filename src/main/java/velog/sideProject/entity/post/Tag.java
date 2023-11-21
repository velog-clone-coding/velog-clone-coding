package velog.sideProject.entity.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_string", length = 45, nullable = false)
    private String tagString;

    @Builder
    public Tag(Long tagId, String tagString) {
        this.tagId = tagId;
        this.tagString = tagString;
    }
}
