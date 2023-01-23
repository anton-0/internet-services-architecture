package com.delinac.filetransfer.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delinac.filetransfer.file.entity.File;

@org.springframework.stereotype.Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
