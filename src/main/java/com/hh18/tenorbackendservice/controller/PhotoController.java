package com.hh18.tenorbackendservice.controller;


import com.hh18.tenorbackendservice.dto.DefaultBooleanDto;
import com.hh18.tenorbackendservice.dto.FileDto;
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
@CrossOrigin(origins = "http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
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
        //all을 검색했을경우 모든값을 반환합니다.
        if (words.equals("all")){
            return photoRepository.findAllByOrderByCreatedAtDesc();
        }
        List<FileDto> fileDtoList = fileService.searchTitle(words);
        SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
        searchKeywordsDto.setKeyword(words);
        //이미 검색된적이 있는 키워드인지 검사
        Boolean exist = searchKeywordsService.isKeywordExist(words);
        if (exist == false) {
            //검색된적이 없을경우 새로운 row생성
            searchKeywordsService.save(searchKeywordsDto);
        } else {
            //검색된적이 있을경우 새로 row를 생성하지않고 존재하는 row의 마지막 접근시간을 수정
            searchKeywordsService.updateKeywordSearchHistory(words);
        }
        return fileDtoList;
    }

    // TODO: 2021-07-21 Long 타입데이터로 마지막 접근시간이 특정 시간(하루,한시간등) 이내일때 +1 값이되는 컬럼 추가해준뒤 해당 값 내림차순정렬으로 트렌드검색어 내려주는 api만들기
}
