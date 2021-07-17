package com.hh18.tenorbackendservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DefaultResDto {
    private boolean res;
    private String msg;
    private Object result;
}
