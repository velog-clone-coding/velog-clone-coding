package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.entity.Member;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WritePageService {

    private final DraftPostRepository draftPostRepository;
    private final DraftTagRepository draftTagRepository;

    /** insert DraftPost with member_id, DraftPostDTO **/
    public Optional<SearchDraftPostDTO> createDraftPost(CreateDraftPostDTO draftPostDTO, Long memberId) {
        log.info("createDraftPost request");
        DraftPost draftPost = draftPostDTO.toDraftPostEntity(Member.builder().memberId(memberId).build());
        List<DraftTag> draftTagList = draftPostDTO.toDraftTagEntity(draftPost);

        DraftPost savedDraftPost = draftPostRepository.save(draftPost);
        List<String> savedDraftTagList = draftTagList.stream()
                .map(draftTagRepository::save)
                .map(DraftTag::getDraftTagString).toList();

        return Optional.ofNullable(SearchDraftPostDTO.toDTO(savedDraftPost, savedDraftTagList));

    }

}