package com.hh18.tenorbackendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private boolean res;
    private String msg;
    private Object user_id;
    private Object user_email;
    private Object user_nickname;
}
