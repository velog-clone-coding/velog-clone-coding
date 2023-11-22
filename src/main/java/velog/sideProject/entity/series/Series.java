package velog.sideProject.entity.series;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import velog.sideProject.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long seriesId;

    @Column(name = "series", unique = true, length = 45, nullable = false)
    private String series;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
//    @Column(name = "member_id", nullable = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Series(Long seriesId, String series, LocalDateTime createdAt, LocalDateTime modifiedAt, Member member) {
        this.seriesId = seriesId;
        this.series = series;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.member = member;
    }
}
