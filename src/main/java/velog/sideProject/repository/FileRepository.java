package velog.sideProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByFileId(Long fileId);
}
