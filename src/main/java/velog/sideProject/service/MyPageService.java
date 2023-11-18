package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.controller.dto.DraftPostDTO;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class  MyPageService {

    private final DraftPostRepository draftPostRepository;

    /** select DraftPostList with member_id**/
    public List<DraftPostDTO> getDraftPostListWithId(Long memberId) {

        List<DraftPost> draftPosts = draftPostRepository.findByMember_MemberId(memberId);

        // draftPost 마다 태그 리스트 조회후 DraftPostWithTagDTO로 묶어서 postInfoList 생성
        List<DraftPostDTO> draftPostDTOList = new ArrayList<>();

        for (DraftPost draftPost : draftPosts) {

            draftPostDTOList.add(DraftPostDTO.toDTO(draftPost, getDraftTagListWithDraftPostId(draftPost.getDraftPostId())));
        }

        return draftPostDTOList;
    }

    /**
     * select DraftPost with post_id & member_id
     **/
    public Optional<DraftPostDTO> getDraftPostWithPostIdMemberId(Long postId, Long memberId) {

        Optional<DraftPost> draftPost = draftPostRepository.findByDraftPostIdAndMember_MemberId(postId, memberId);

        return draftPost.map(draftPostItem -> {
            List<String> tagList = getDraftTagListWithDraftPostId(draftPostItem.getDraftPostId());
            return DraftPostDTO.toDTO(draftPostItem, tagList);
        });
    }

    /**
     * DraftPost에 맞는 tag 찾는 메서드
     **/
    private List<String> getDraftTagListWithDraftPostId(Long draftPostId) {

        List<DraftTag> draftTagList = draftPostRepository.findTagStringsByPostId(draftPostId);

        return draftTagList.stream()
                .map(DraftTag::getDraftTagString)
                .toList();
    }


}
