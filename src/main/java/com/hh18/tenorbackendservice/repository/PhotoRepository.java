package com.hh18.tenorbackendservice.repository;

import com.hh18.tenorbackendservice.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    List<Photo> findAllByOrderByCreatedAtDesc();
}
