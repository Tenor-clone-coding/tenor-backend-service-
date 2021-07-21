package com.hh18.tenorbackendservice.repository;

import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findAllByOrderByCreatedAtDesc();
}
