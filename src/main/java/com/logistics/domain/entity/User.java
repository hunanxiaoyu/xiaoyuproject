package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "userInfo")
public class User {
    @TableId(type = IdType.ASSIGN_ID )
    private String id;
    private String username;
    private String password;
    private String avatar;
    private String role;
    private String permission;
    private String token;
}
