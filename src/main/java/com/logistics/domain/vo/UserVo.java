package com.logistics.domain.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserVo {
    private String username;
    private String avatar;
    private String role;
    private String permission;
    private String token;
}
