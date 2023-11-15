package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.controller.dto.DraftPostDTO;
import velog.sideProject.controller.dto.DraftPostWithTagDTO;
import velog.sideProject.controller.dto.DraftTagDTO;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final DraftPostRepository draftPostRepository;

    /** select DraftPost List with member_id**/
    public List<DraftPostWithTagDTO> getDraftPostWithId(Long member_id) {
        List<DraftPostWithTagDTO> postInfoList = new ArrayList<>();

        List<DraftPost> draftPosts = draftPostRepository.findByMember_MemberId(member_id);

        for (DraftPost draftPost : draftPosts) {

            List<DraftTag> draftTagList = draftPostRepository.findTagStringsByPostId(draftPost.getDraftPostId());

            List<DraftTagDTO> draftPostDTOList = draftTagList.stream()
                .map(DraftTagDTO::toDTO)
                .toList();

            postInfoList.add(new DraftPostWithTagDTO(DraftPostDTO.toDTO(draftPost), draftPostDTOList));
        }

        return postInfoList;
    }


}
