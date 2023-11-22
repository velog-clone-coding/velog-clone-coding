package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import velog.sideProject.controller.dto.FileDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonController {

    @GetMapping("/file/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        // 파일이 비어있을 때
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        // 파일이 있을 때 다른 로직 수행
        FileDTO fileDTO = FileDTO.builder()
                .fileId(1L)
                .url("https://i.imgur.com/rDZbFUA.png")
                .build();

        return ResponseEntity.ok(fileDTO);
    }
}
