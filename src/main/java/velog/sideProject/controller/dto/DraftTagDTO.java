package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.drfatPost.DraftTag;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class DraftTagDTO {

    private final String typeName = "draftTagDTO";
    private String tagName;

    @Builder
    public DraftTagDTO(String tagName) {
        this.tagName = tagName;
    }

    /** TODO: memberId 이렇게 넣는게 맞는지 확인필요 **/
    public DraftTag toEntity(){
        return DraftTag.builder()
                .draftTagString(tagName)
                .build();

    }

    public static DraftTagDTO toDTO(DraftTag draftTag) {
        return DraftTagDTO.builder()
                .tagName(draftTag.getDraftTagString())
                .build();
    }
}