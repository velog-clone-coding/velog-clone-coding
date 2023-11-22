package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.Member;
import velog.sideProject.global.entity.Authority;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SearchMemberDTO {

    private final String typeName = "SearchMemberDTO";
    private Long memberId;
    private String memberName;

    private String velogTitle;
    private String shortIntro; // 한줄 소개
    private String introduction; // 소개

    private String memberEmail;
    private String socialEmail;
    private String socialFacebook;
    private String socialGithub;
    private String socialTwitter;
    private String socialWebsite;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Boolean replyAlert;
    private Boolean updateAlert;

    private String profile; // 이미지 경로
    private String authority; // 권한 정보


    @Builder
    public SearchMemberDTO(Long memberId, String memberName, String velogTitle, String shortIntro, String introduction, String memberEmail, String socialEmail, String socialFacebook, String socialGithub, String socialTwitter, String socialWebsite, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean replyAlert, Boolean updateAlert, String profile, String authority) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.velogTitle = velogTitle;
        this.shortIntro = shortIntro;
        this.introduction = introduction;
        this.memberEmail = memberEmail;
        this.socialEmail = socialEmail;
        this.socialFacebook = socialFacebook;
        this.socialGithub = socialGithub;
        this.socialTwitter = socialTwitter;
        this.socialWebsite = socialWebsite;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.replyAlert = replyAlert;
        this.updateAlert = updateAlert;
        this.profile = profile;
        this.authority = authority;
    }

    public static SearchMemberDTO toDTO(Member member) {
        return SearchMemberDTO.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .velogTitle(member.getVelogTitle())
                .shortIntro(member.getShortIntro())
                .introduction(member.getIntroduction())
                .memberEmail(member.getMemberEmail())
                .socialFacebook(member.getSocialFacebook())
                .socialGithub(member.getSocialGithub())
                .socialTwitter(member.getSocialTwitter())
                .socialWebsite(member.getSocialWebsite())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .replyAlert(member.getReplyAlert())
                .updateAlert(member.getUpdateAlert())
                .profile(member.getFile().getFilePath())
                .authority(member.getAuthority().name())
                .build();
    }

}
