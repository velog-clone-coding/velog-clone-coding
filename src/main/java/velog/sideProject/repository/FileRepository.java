package velog.sideProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}