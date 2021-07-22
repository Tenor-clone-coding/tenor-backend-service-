package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.DefaultBooleanDto;
import com.hh18.tenorbackendservice.dto.HeaderDto;
import com.hh18.tenorbackendservice.dto.SignupRequestDto;
import com.hh18.tenorbackendservice.dto.UserInfoDto;
import com.hh18.tenorbackendservice.jwt.JwtTokenProvider;
import com.hh18.tenorbackendservice.models.User;
import com.hh18.tenorbackendservice.repository.UserRepository;
import com.hh18.tenorbackendservice.security.UserDetailsImpl;
import com.hh18.tenorbackendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin(origins = "http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository; // for jwt test
    private final JwtTokenProvider jwtTokenProvider;



    // 카카오로 로그인 라우팅
    @GetMapping("/user/kakao/callback")
    @ResponseBody
    public HeaderDto kakaoLogin(@RequestParam(value = "code") String code) {
        String username = userService.kakaoLogin(code);
        User member = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("가입되지않은 아이디입니다."));
        HeaderDto headerDto = new HeaderDto();
        headerDto.setTOKEN(jwtTokenProvider.createToken(member.getUsername(),member.getId()));
        return headerDto;
    }

    @PostMapping("/user/login")
    @ResponseBody
    public HeaderDto login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));
        HeaderDto headerDto = new HeaderDto();
        headerDto.setTOKEN(jwtTokenProvider.createToken(member.getUsername(),member.getId()));
        return headerDto;
    }
    @GetMapping("/test")
    @ResponseBody
    public DefaultBooleanDto test() {
        DefaultBooleanDto defaultBooleanDto = new DefaultBooleanDto();
        defaultBooleanDto.setRes(true);
        return defaultBooleanDto;
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
    @ResponseBody
    public boolean registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return true;
    }

    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
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
