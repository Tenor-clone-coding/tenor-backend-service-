package com.hh18.tenorbackendservice.service;

import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.models.Photo;
import com.hh18.tenorbackendservice.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Transactional
    public Long save(PhotoDto photoDto){
        return photoRepository.save(photoDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지않는사진입니다.")
        );
        photoRepository.delete(photo);
    }
}
