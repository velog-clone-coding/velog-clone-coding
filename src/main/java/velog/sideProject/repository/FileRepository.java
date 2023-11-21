package velog.sideProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}