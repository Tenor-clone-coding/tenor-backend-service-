package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.FileDto;
import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.service.FileService;
import com.hh18.tenorbackendservice.service.PhotoService;
import com.hh18.tenorbackendservice.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
public class PhotoUploadController {

    private final PhotoService photoService;
    private final FileService fileService;

    @PostMapping("/api/photos")
    public FileDto write(@RequestParam("file") MultipartFile files, PhotoDto photoDto) {
        try {
            String origFilename = files.getOriginalFilename();
            //파일명을 MD5해쉬로 변환하고
            String nameToMD5 = new MD5Generator(origFilename).toString();
            //랜덤키를 생성해서
            String uuid = UUID.randomUUID().toString();
            //파일명과 합쳐 중복파일명을 피함
            String filename = nameToMD5 + "_" +uuid;
            /* static/image에 저장됨 */
//            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image"; //윈도우용
            String savePath = System.getProperty("user.dir") + "/image"; //리눅스용
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new java.io.File(savePath).exists  ()) {
                try{
                    new java.io.File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "/" + filename;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOriginFname(origFilename);
            fileDto.setFname(filename);
            fileDto.setFPath(filePath);
            fileDto.setTitle(photoDto.getTitle());

            Long fileId = fileService.saveFile(fileDto);
            photoDto.setFileId(fileId);
            photoService.save(photoDto);
            //파일dto photodto 합치면될듯?
            fileDto.setId(fileId);
            //Id는 db가 지정해주기에 지정하면 안되지만 저장할게 아니니 상관없겠지? 물론 이게 좋은방법일리는 없다.
            return fileDto;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
