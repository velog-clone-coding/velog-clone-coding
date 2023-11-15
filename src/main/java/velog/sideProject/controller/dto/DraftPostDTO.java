package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftPost;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DraftPostDTO {

    private final String typeName = "draftPostDTO";
    private Long memberId;
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime agoDate;

    @Builder
    public DraftPostDTO(Long memberId, Long postId, String title, String content, LocalDateTime agoDate) {
        this.memberId = memberId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.agoDate = agoDate;
    }

    /** TODO: memberId 이렇게 넣는게 맞는지 확인필요 **/
    public DraftPost toEntity(){
        return DraftPost.builder()
                .draftPostTitle(title)
                .draftPostContent(content)
                .draftPostModifiedAt(agoDate)
                .member(Member.builder().memberId(memberId).build())
                .build();

    }

    public static DraftPostDTO toDTO(DraftPost draftPost) {
        return DraftPostDTO.builder()
                .memberId(draftPost.getMember().getMemberId())
                .postId(draftPost.getDraftPostId())
                .title(draftPost.getDraftPostTitle())
                .content(draftPost.getDraftPostContent())
                .agoDate(draftPost.getDraftPostModifiedAt())
                .build();
    }
}
