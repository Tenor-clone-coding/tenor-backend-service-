package com.hh18.tenorbackendservice.service;

import com.hh18.tenorbackendservice.dto.FileDto;
import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.models.Photo;
import com.hh18.tenorbackendservice.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;


    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public String searchById(Long id){
        File file = fileRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지않는파일")
        );
        return file.getFname();
    }

    @Transactional
    public List<File> readAll(){
        return fileRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public List<FileDto> searchTitle(String words){
        List<File> files = fileRepository.findByTitleContainingOrderByCreatedAtDesc(words);
        List<FileDto> fileDtoList = new ArrayList<>();

        if(files.isEmpty()){
            return fileDtoList;
        }

        for(File file : files){
            fileDtoList.add(this.entityDto(file));
        }
        return fileDtoList;
    }
    private FileDto entityDto(File file){
        return FileDto.builder()
                .id(file.getId())
                .title(file.getTitle())
                .fname(file.getFname())
                .build();
    }
}
