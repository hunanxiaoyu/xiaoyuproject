package com.logistics.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.Console;
import com.logistics.domain.vo.OrderVo;
import com.logistics.domain.vo.UserVo;
import com.logistics.service.AdminService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;
  private static final Logger log = Logger.getLogger(AdminController.class.getName());
    // 获取验证码
    @GetMapping("/captcha")
    public ResponseResult<Map<String,String>> getCodeKey(){
      ResponseResult<Map<String, String>> codeKey = adminService.getCodeKey();
      log.info(codeKey.getMsg());
      return codeKey;
    }
  @PostMapping("/login")
  public ResponseResult<UserVo> login (@NotNull String username, @NotNull String password, @NotNull String captcha, @NotNull String codeIdentifier) {
    ResponseResult<UserVo> login= adminService.login(username, password, captcha, codeIdentifier);
    log.info(login.getMsg());
    return login;
  }
  @PostMapping("/register")
  public ResponseResult<Void> register(@NotNull String username, @NotNull String password, @NotNull String captcha, @NotNull String codeIdentifier) {
      adminService.register(username,password,captcha,codeIdentifier);
      return ResponseResult.ok();
  }
  @GetMapping("/getAllorderInfo")
  public ResponseResult<List<OrderVo>> getOrder(){

            return adminService.getOrderInfo();
  }
}

