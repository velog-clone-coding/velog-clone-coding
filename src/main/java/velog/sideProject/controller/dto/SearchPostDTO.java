package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.post.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SearchPostDTO {

    private final String typeName = "SearchPostDTO";
    private Long postId;
    private SearchMemberDTO member;
    private String title;
    private String content;
    private LocalDateTime modifedAt;
    private LocalDateTime createdAt;

    private int postCount;
    private int replyCount;
    private Long likeCount;

    private String thumbnailPath;
    private String seriesName;

    private List<String> tagList;

    // 게시글 생성시 사용
    public static SearchPostDTO toDTO(Post post, List<String> tagList) {
        return SearchPostDTO.builder()
                .member(SearchMemberDTO.toDTO(post.getMember()))
                .postId(post.getPostId())
                .title(post.getPostTitle())
                .content(post.getPostContent())
                .modifedAt(post.getModifiedAt())
                .createdAt(post.getCreatedAt())
                .postCount(post.getPostCount())
                .replyCount(0)
                .likeCount(0L)
                .thumbnailPath(post.getFile() != null ? post.getFile().getFilePath() : null)
                .seriesName(post.getSeries() != null ? post.getSeries().getSeries() : null)
                .tagList(tagList)
                .build();
    }

    /** TODO: replyCount 추가 필요, count 조회 어떻게 할지 고려 필요 **/
    // 게시글 조회시 사용
    public static SearchPostDTO toDTO(Post post, List<String> tagList, Long likeCount) {
        return SearchPostDTO.builder()
                .member(SearchMemberDTO.toDTO(post.getMember()))
                .postId(post.getPostId())
                .title(post.getPostTitle())
                .content(post.getPostContent())
                .modifedAt(post.getModifiedAt())
                .createdAt(post.getCreatedAt())
                .replyCount(-99999)
                .likeCount(likeCount)
                .tagList(tagList)
                .build();
    }

    @Builder
    public SearchPostDTO(Long postId, SearchMemberDTO member, String title, String content, int postCount, LocalDateTime modifedAt, LocalDateTime createdAt, int replyCount, Long likeCount, String thumbnailPath, String seriesName, List<String> tagList) {
        this.postId = postId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.postCount = postCount;
        this.modifedAt = modifedAt;
        this.createdAt = createdAt;
        this.replyCount = replyCount;
        this.likeCount = likeCount;
        this.thumbnailPath = thumbnailPath;
        this.seriesName = seriesName;
        this.tagList = tagList;
    }
}
