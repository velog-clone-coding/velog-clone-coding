package velog.sideProject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.global.entity.Authority;
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

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_email", nullable = false)
    private String memberEmail;
    @Column(name = "velog_title", length = 100, nullable = false)
    private String velogTitle;

    @Column(name = "introduction", length = 500)
    private String introduction;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "short_intro", length = 150)
    private String shortIntro;

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

    @Column(name = "reply_alert")
    private Boolean replyAlert;

    @Column(name = "update_alert")
    private Boolean updateAlert;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    /**
     * 테스트 용
     * TODO: jwt로 유저 식별 이후 삭제 필요
     * **/

    @Builder
    public Member(Long memberId, String memberEmail, String memberName, String velogTitle, Authority authority) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.velogTitle = velogTitle;
        this.authority = authority;
    }
}