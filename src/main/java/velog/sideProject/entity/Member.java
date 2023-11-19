package velog.sideProject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_email", nullable = false)
    private String memberEmail;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "short_intro", length = 150)
    private String shortIntro;

    @Column(name = "introduction", length = 500)
    private String introduction;

    @Column(name = "social_email", length = 255)
    private String socialEmail;

    @Column(name = "social_github", length = 255)
    private String socialGithub;

    @Column(name = "social_twitter", length = 255)
    private String socialTwitter;

    @Column(name = "social_facebook", length = 255)
    private String socialFacebook;

    @Column(name = "social_website", length = 255)
    private String socialWebsite;

    @Column(name = "velog_title", nullable = false, length = 100)
    private String velogTitle;

    @Column(name = "reply_alert", nullable = false)
    private Boolean replyAlert;

    @Column(name = "update_alert", nullable = false)
    private Boolean updateAlert;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    //@Column(name = "image_id", nullable = false)
    private File file;

    @ManyToOne
    @JoinColumn(name = "authority_id", nullable = false)
    //@Column(name = "autohorityKey", nullable = false)
    private Authority autohority;


    /**
     * 테스트 용
     * TODO: jwt로 유저 식별 이후 삭제 필요
     * **/

    @Builder
    public Member(Long memberId) {
        this.memberId = memberId;
    }
}
