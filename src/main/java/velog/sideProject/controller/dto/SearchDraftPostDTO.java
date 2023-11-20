package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SearchDraftPostDTO {

    private final String typeName = "draftPostDTO";
    private Long memberId;
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime agoDate;
    private List<String> tagList;

    @Builder
    public SearchDraftPostDTO(Long memberId, Long postId, String title, String content, LocalDateTime agoDate, List<String> tagList) {
        this.memberId = memberId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.agoDate = agoDate;
        this.tagList = tagList;
    }

    public static SearchDraftPostDTO toDTO(DraftPost draftPost, List<String> tagList) {
        return SearchDraftPostDTO.builder()
                .memberId(draftPost.getMember().getMemberId())
                .postId(draftPost.getDraftPostId())
                .title(draftPost.getDraftPostTitle())
                .content(draftPost.getDraftPostContent())
                .agoDate(draftPost.getDraftPostModifiedAt())
                .tagList(tagList)
                .build();
    }

    @Override
    public String toString() {
        return "SearchDraftPostDTO{" +
                "typeName='" + typeName + '\'' +
                ", memberId=" + memberId +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", agoDate=" + agoDate +
                ", tagList=" + tagList +
                '}';
    }
//    public DraftPost toDraftPostEntity(Member member){
//        return DraftPost.builder()
//                .draftPostTitle(title)
//                .draftPostContent(content)
//                .draftPostModifiedAt(agoDate)
//                .member(member)
//                .build();
//
//    }
//
//    public List<DraftTag> toDraftTagEntity(DraftPost draftPost) {
//        return Optional.ofNullable(tagList).orElse(Collections.emptyList())
//                .stream()
//                .map(tagString -> DraftTag.builder()
//                        .draftTagString(tagString)
//                        .draftPost(draftPost).build())
//                .toList();
//    }
}
