package velog.sideProject.entity.post;

import jakarta.persistence.*;
import velog.sideProject.entity.File;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.series.Series;

import java.time.LocalDateTime;

@Entity
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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

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
//    @Column(name = "thumbnail", nullable = false)
    private File file;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
}
