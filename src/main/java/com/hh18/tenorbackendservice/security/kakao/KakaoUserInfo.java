package com.hh18.tenorbackendservice.security.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KaKaoUserInfo {
    Long id;
    String email;
    String nickname;
}
