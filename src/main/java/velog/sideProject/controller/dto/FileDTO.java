package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDTO {
    Long fileId;
    String url;

    @Builder
    public FileDTO(Long fileId, String url) {
        this.fileId = fileId;
        this.url = url;
    }
}
