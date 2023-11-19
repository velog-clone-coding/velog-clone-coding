package velog.sideProject.controller.dto;

import lombok.Builder;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CreateDraftPostDTO {

    private final String typeName = "draftPostDTO";
    private String title;
    private String content;
    private List<String> tagList;

    @Builder
    public CreateDraftPostDTO(String title, String content, List<String> tagList) {
        this.title = title;
        this.content = content;
        this.tagList = tagList;
    }


    public DraftPost toDraftPostEntity(Member member){
        return DraftPost.builder()
                .draftPostTitle(title)
                .draftPostContent(content)
                .member(member)
                .build();

    }

    public List<DraftTag> toDraftTagEntity(DraftPost draftPost) {
        return Optional.ofNullable(tagList).orElse(Collections.emptyList())
                .stream()
                .map(tagString -> DraftTag.builder()
                        .draftTagString(tagString)
                        .draftPost(draftPost).build())
                .toList();
    }


}
