package com.hh18.tenorbackendservice.service;

import com.hh18.tenorbackendservice.dto.FileDto;
import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

}
