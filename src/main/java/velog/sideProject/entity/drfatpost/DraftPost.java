package velog.sideProject.entity.drfatpost;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import velog.sideProject.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class DraftPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "draft_post_id")
    private Long draftPostId;

    @Column(name = "draft_post_title",length = 255, nullable = false)
    private String draftPostTitle;

    @Column(name = "draft_post_content",columnDefinition = "TEXT", nullable = false)
    private String draftPostContent;

    @CreatedDate
    @UpdateTimestamp
    @Column(name = "draft_post_modified_at", nullable = false)
    private LocalDateTime draftPostModifiedAt;

    @OneToMany(mappedBy = "draftPost")
    private List<DraftTag> draftTagList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
//    @Column(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public DraftPost(String draftPostTitle, String draftPostContent, LocalDateTime draftPostModifiedAt, Member member) {
        this.draftPostTitle = draftPostTitle;
        this.draftPostContent = draftPostContent;
        this.draftPostModifiedAt = draftPostModifiedAt;
        this.member = member;
    }
}
