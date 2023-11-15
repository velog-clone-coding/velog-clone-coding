package velog.sideProject.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DraftPostWithTagDTO {

    private final DraftPostDTO draftPostDTO;
    private final List<DraftTagDTO> draftTagDTOList;

}
