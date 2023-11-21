package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class  MyPageService {

    private final DraftPostRepository draftPostRepository;

    /** select DraftPostList with member_id**/
    public List<SearchDraftPostDTO> getDraftPostListWithId(Long memberId) {

        List<DraftPost> draftPosts = draftPostRepository.findByMember_MemberId(memberId);


        // draftPost 마다 태그 리스트 조회후 DraftPostDTOList 생성
        return draftPosts.stream()
                .map(draftPost -> SearchDraftPostDTO.toDTO(draftPost, getDraftTagListWithDraftPostId(draftPost)))
                .toList();
    }

    /** select DraftPost with post_id & member_id **/
    public Optional<SearchDraftPostDTO> getDraftPostWithPostIdMemberId(Long postId, Long memberId) {

        Optional<DraftPost> draftPost = draftPostRepository.findByDraftPostIdAndMember_MemberId(postId, memberId);

        // DraftPostDTO에 DraftPost에 맞는 DraftTagList넣기
        return draftPost.map(draftPostItem -> {
            List<String> tagList = getDraftTagListWithDraftPostId(draftPostItem);
            return SearchDraftPostDTO.toDTO(draftPostItem, tagList);
        });
    }

    /**
     * delete DraftPost with member_id & post_id
     */
    public Long deleteDraftPostWithPostIdMemberId(Long postId, Long memberId) {
        return draftPostRepository.deleteByDraftPostIdAndMember_MemberId(postId, memberId);
    }


    // DraftPost에 맞는 tag 찾는 메서드
    private List<String> getDraftTagListWithDraftPostId(DraftPost draftPost) {

        // DraftPost에 맞는 DraftTagList 찾기
        List<DraftTag> draftTagList = draftPost.getDraftTagList();

        List<String> list = draftTagList.stream()
                .map(DraftTag::getDraftTagString)
                .toList();

        return list;
    }


}