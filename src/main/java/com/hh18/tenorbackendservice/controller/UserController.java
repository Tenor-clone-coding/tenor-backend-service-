package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.DefaultBooleanDto;
import com.hh18.tenorbackendservice.dto.SignupRequestDto;
import com.hh18.tenorbackendservice.dto.UserInfoDto;
import com.hh18.tenorbackendservice.security.UserDetailsImpl;
import com.hh18.tenorbackendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Controller
@CrossOrigin(origins = "http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 중복 확인
    @GetMapping("/user/checkid/{id}")
    @ResponseBody
    public DefaultBooleanDto checkId(@PathVariable String userEmail) {
        Boolean response = userService.checkEmail(userEmail);
        DefaultBooleanDto responseDto = new DefaultBooleanDto();
        responseDto.setRes(response);
        return responseDto;
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/";
    }

    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    // 카카오로 로그인 라우팅
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam(value = "code") String code, HttpServletResponse httpServletResponse) {
        userService.kakaoLogin(code);
        httpServletResponse.addHeader("Access-Control-Allow-Origin","http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","*");
        return "redirect:http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com/";
    }

    @GetMapping("user/info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return new UserInfoDto(false, "로그인상태가 아닙니다.", null, null, null);
        } else {
            return new UserInfoDto(true, "로그인된 사용자입니다.",
                    userDetails.getUser().getId(),
                    userDetails.getUser().getEmail(),
                    userDetails.getUser().getUsername());
        }
    }

}
