package velog.sideProject.controller.dto;

import lombok.*;
import velog.sideProject.entity.File;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.post.Post;
import velog.sideProject.entity.post.Tag;
import velog.sideProject.entity.series.Series;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CreatePostDTO {

    private final String typeName = "CreatePostDTO";

    private String title;
    private String content;
    private String desc;
    private Boolean isPublic;
    private String urlSlug;
    private Long thumbnailId;
    private Long seriesId;
    private List<String> tagList;

    @Builder
    public CreatePostDTO(String title, String content, String desc, Boolean isPublic, String urlSlug, Long thumbnailId, Long seriesId, List<String> tagList) {
        this.title = title;
        this.content = content;
        this.desc = desc;
        this.isPublic = isPublic;
        this.urlSlug = urlSlug;
        this.thumbnailId = thumbnailId;
        this.seriesId = seriesId;
        this.tagList = tagList;
    }

    public Post toPostEntity(Member member, File file, Series series){
        return Post.builder()
                .postTitle(title)
                .postContent(content)
                .postDesc(desc)
                .postPublic(isPublic)
                .postSlug(urlSlug)
                .postCount(0) // 생성시 조회수는 0
                .file(file)
                .series(series)
                .member(member)
                .build();
    }

    public List<Tag> toTagEntity() {
        return Optional.ofNullable(tagList).orElse(Collections.emptyList()).stream()
                .map(tagString -> Tag.builder()
                        .tagString(tagString)
                        .build())
                .toList();
    }
}
