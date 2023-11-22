package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.CreatePostDTO;
import velog.sideProject.controller.dto.SearchPostDTO;
import velog.sideProject.entity.File;
import velog.sideProject.entity.Member;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.entity.post.Post;
import velog.sideProject.entity.post.Post_Tag;
import velog.sideProject.entity.post.Tag;
import velog.sideProject.entity.post.idclass.Post_TagPK;
import velog.sideProject.entity.series.Series;
import velog.sideProject.repository.FileRepository;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;
import velog.sideProject.repository.member.MemberRepository;
import velog.sideProject.repository.post.jpa.PostRepository;
import velog.sideProject.repository.post.jpa.Post_TagRepository;
import velog.sideProject.repository.post.jpa.TagRepository;
import velog.sideProject.repository.series.jpa.SeriesRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final Post_TagRepository postTagRepository;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final SeriesRepository seriesRepository;


    /** insert Post, Tag, Post_Tag with member_id, createPostDTO **/
    public Optional<SearchPostDTO> createPost(CreatePostDTO createPostDTO, Long memberId) {
        log.info("Service createPost request");

        // 멤버 조회
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        // 썸네일 조회
        Optional<File> thumbnailFile = fileRepository.findByFileId(createPostDTO.getThumbnailId());
        // 시리즈 조회
        Optional<Series> series = seriesRepository.findBySeriesIdAndMember_MemberId(createPostDTO.getSeriesId(), memberId);

        // 태그, 게시글 정보 분리
        Post post = createPostDTO.toPostEntity(member.get(), thumbnailFile.orElse(null), series.orElse(null));
        List<Tag> tagList = createPostDTO.toTagEntity();

        // urlSlug 확인
        post.setPostSlug(createUrlSlugCheck(post.getPostSlug()));

        // desc 확인
        post.setPostDesc(createDescCheck(post.getPostDesc()));

        // 게시글 저장
        Post savedPost = postRepository.save(post);

        // DB에 태그 정보를 찾고 없다면 등록하고, 등록된 태그 리스트를 생성
        List<Tag> savedTagList = tagList.stream().map(tag -> {
            Tag savedTag = tagRepository.findByTagString(tag.getTagString())
                    .orElseGet(() -> tagRepository.save(tag));
            return savedTag;
        }).toList();

        // 등록 되어 있는 태그 아이디와 현재 게시글 아이디를 통해 post_tag 테이블에 값 추가
        savedTagList.forEach(tag -> {
            Post_Tag postTag = Post_Tag.builder()
                    .postTagPK(new Post_TagPK(post.getPostId(), tag.getTagId()))
                    .post(savedPost)
                    .tag(tag)
                    .build();

            // post_tag 테이블에 정보 저장
            postTagRepository.save(postTag);
        });

        // 태그 리스트 List<String>로 변경(DTO를 생성하는데 사용)
        List<String> tagStringList = savedTagList.stream()
                .map(Tag::getTagString)
                .toList();

        // 생성된 게시글 정보 리턴
        SearchPostDTO searchPostDTO = SearchPostDTO.toDTO(savedPost, tagStringList);
        return Optional.ofNullable(searchPostDTO);
    }


    // desc값이 비면 내용에 txt로 변경 /** TODO : 이후 개선 **/
    private String createDescCheck(String postDesc) {

        return postDesc;
    }

    // urlSlug 확인 후 null 또는 size가 0이라면 게시글 제목으로
    // 게시글 제목이 겹칠 경우 -랜덤숫자로 변경
    /** TODO : 이후 개선 **/
    private String createUrlSlugCheck(String urlSlug) {


        return urlSlug;
    }
}
