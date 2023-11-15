package velog.sideProject.entity.series;

import jakarta.persistence.*;
import velog.sideProject.entity.Member;

import java.time.LocalDateTime;

@Entity
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long seriesId;

    @Column(name = "series", unique = true, length = 45, nullable = false)
    private String series;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
//    @Column(name = "member_id", nullable = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
