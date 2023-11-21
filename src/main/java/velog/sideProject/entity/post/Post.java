package velog.sideProject.entity.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import velog.sideProject.entity.File;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftTag;
import velog.sideProject.entity.series.Series;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title", length = 255, nullable = false)
    private String postTitle;

    @Column(name = "post_content", length = 255, nullable = false)
    private String postContent;

    @Column(name = "post_desc", length = 150, nullable = false)
    private String postDesc;

    @Column(name = "post_count", nullable = false)
    private int postCount;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "post_public", nullable = false)
    private boolean postPublic;

    @Column(name = "post_slug", length = 100, nullable = false)
    private String postSlug;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
//    @Column(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "file_id")
//    @Column(name = "thumbnail")
    private File file;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @Builder
    public Post(Long postId, String postTitle, String postContent, String postDesc, int postCount, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean postPublic, String postSlug, Member member, File file, Series series) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postDesc = postDesc;
        this.postCount = postCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.postPublic = postPublic;
        this.postSlug = postSlug;
        this.member = member;
        this.file = file;
        this.series = series;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postDesc='" + postDesc + '\'' +
                ", postCount=" + postCount +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", postPublic=" + postPublic +
                ", postSlug='" + postSlug + '\'' +
                ", member=" + member.toString() +
                ", file=" + file +
                ", series=" + series +
                '}';
    }
}
