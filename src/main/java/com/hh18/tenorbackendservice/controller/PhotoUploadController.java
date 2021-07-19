package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.FileDto;
import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.service.FileService;
import com.hh18.tenorbackendservice.service.PhotoService;
import com.hh18.tenorbackendservice.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class PhotoUploadController {

    private final PhotoService photoService;
    private final FileService fileService;

    @PostMapping("/api/photos")
    public String write(@RequestParam("file") MultipartFile files, PhotoDto photoDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* static/image에 저장됨 */
            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new java.io.File(savePath).exists()) {
                try{
                    new java.io.File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOriginFname(origFilename);
            fileDto.setFname(filename);
            fileDto.setFPath(filePath);

            Long fileId = fileService.saveFile(fileDto);
            photoDto.setFileId(fileId);
            photoService.save(photoDto);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
