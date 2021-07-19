package com.hh18.tenorbackendservice.controller;


import com.hh18.tenorbackendservice.dto.DefaultBooleanDto;
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
    private final PhotoService photoService;


    @GetMapping("api/photos")
    public List<Photo> readPhotoList(PhotoDto requestDto) {
        return photoRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("api/photos/{id}")
    public String searchById(@PathVariable Long id) {
        return fileService.searchById(id);
    }

    @DeleteMapping("api/photos/{id}")
    public DefaultBooleanDto delete(@PathVariable Long id) {
        DefaultBooleanDto response = new DefaultBooleanDto();
        try {
            photoService.delete(id);
            response.setRes(true);
        } catch (Exception e) {
            response.setRes(false);
        }
        return response;
    }

    @GetMapping(value = "api/search")
    public List<? extends Object> search(@RequestParam(value = "words") String words) {
        if (words.equals("all")){
            return photoRepository.findAllByOrderByCreatedAtDesc();
        }
        List<PhotoDto> photoDtoList = photoService.searchTitle(words);
        return photoDtoList;
    }
}
