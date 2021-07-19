package com.hh18.tenorbackendservice.controller;


import com.hh18.tenorbackendservice.dto.FileDto;
import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.models.Photo;
import com.hh18.tenorbackendservice.repository.FileRepository;
import com.hh18.tenorbackendservice.repository.PhotoRepository;
import com.hh18.tenorbackendservice.service.FileService;
import com.hh18.tenorbackendservice.service.PhotoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoRepository photoRepository;
    private final FileService fileService;


    @GetMapping("api/photos")
    public List<Photo> readPhotoList(PhotoDto requestDto){
        return photoRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("api/photos/{id}")
    public String searchById(@PathVariable Long id){
        return fileService.searchById(id);
    }


}
