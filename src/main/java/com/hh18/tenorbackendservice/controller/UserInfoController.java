package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.UserInfoDto;
import com.hh18.tenorbackendservice.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @GetMapping("user/info")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails == null){
            return new UserInfoDto(false,"로그인상태가 아닙니다.",null,null,null);
        } else {
            return new UserInfoDto(true,"로그인된 사용자입니다.",
                    userDetails.getUser().getId(),
                    userDetails.getUser().getEmail(),
                    userDetails.getUser().getUsername());
        }
    }

}
