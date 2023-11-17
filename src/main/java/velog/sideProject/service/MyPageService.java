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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final DraftPostRepository draftPostRepository;

    /** select DraftPost List with member_id**/
    public List<DraftPostDTO> getDraftPostWithId(Long member_id) {

        List<DraftPost> draftPosts = draftPostRepository.findByMember_MemberId(member_id);

        // draftPost 마다 태그 리스트 조회후 DraftPostWithTagDTO로 묶어서 postInfoList 생성
        List<DraftPostDTO> draftPostDTOList = new ArrayList<>();

        for (DraftPost draftPost : draftPosts) {
            List<DraftTag> draftTagList = draftPostRepository.findTagStringsByPostId(draftPost.getDraftPostId());

            List<String> tagNames = draftTagList.stream()
                    .map(DraftTag::getDraftTagString)
                    .toList();

            draftPostDTOList.add(DraftPostDTO.toDTO(draftPost, tagNames));
        }

        return draftPostDTOList;
    }


}
