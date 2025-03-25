package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.User;
import com.logistics.domain.vo.OrderVo;
import com.logistics.domain.vo.UserVo;
import com.logistics.utils.ResponseResult;

import java.util.List;
import java.util.Map;

public interface AdminService extends IService<User> {
    ResponseResult<Map<String,String>> getCodeKey();

    ResponseResult<UserVo> login(String username, String password, String captcha, String codeIdentifier);

    void register(String username, String password, String captcha, String codeIdentifier);


}
