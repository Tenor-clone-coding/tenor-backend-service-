package com.hh18.tenorbackendservice.controller;


import com.hh18.tenorbackendservice.dto.DefaultBooleanDto;
import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.dto.SearchKeywordsDto;
import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.repository.PhotoRepository;
import com.hh18.tenorbackendservice.service.FileService;
import com.hh18.tenorbackendservice.service.PhotoService;

import com.hh18.tenorbackendservice.service.SearchKeywordsService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoRepository photoRepository;
    private final FileService fileService;
    private final PhotoService photoService;
    private final SearchKeywordsService searchKeywordsService;


    @GetMapping("api/photos")
    public List<File> readPhotoList() {
        return fileService.readAll();
    }

    @GetMapping("api/photos/{id}")
    public String searchById(@PathVariable Long id) {
        return fileService.searchById(id);
    }

    //get all img srcs
    @GetMapping("api/photos/all")
    public List<String> searchAllById() { return fileService.searchAllById();}

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
        SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
        searchKeywordsDto.setKeyword(words);
        searchKeywordsService.save(searchKeywordsDto);
        return photoDtoList;
    }
}
