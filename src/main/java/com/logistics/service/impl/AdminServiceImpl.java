package com.logistics.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.entity.User;
import com.logistics.domain.vo.OrderVo;
import com.logistics.domain.vo.UserVo;
import com.logistics.mapper.UserMapper;
import com.logistics.service.AdminService;
import com.logistics.utils.JWTUtils;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ServiceImpl<UserMapper,User> implements AdminService {
    private final StringRedisTemplate redisTemplate;
    @Override
    public ResponseResult<Map<String,String>> getCodeKey(){
        Map<String,String> map=new HashMap<>();
        // 生成key( 图形验证码 )
        CircleCaptcha circleCaptcha= CaptchaUtil.createCircleCaptcha(130, 44);
        String imageBase64= circleCaptcha.getImageBase64();
        // 生成唯一标识方便用户下次能找到他的图形验证码
        String codeIdentifier = UUID.randomUUID().toString();
        // 将生成的验证码存入redis
        redisTemplate.opsForValue().set("captcha:codeKey"+codeIdentifier,circleCaptcha.getCode(),60, TimeUnit.HOURS);
        String code= circleCaptcha.getCode();
        map.put("code","data:image/png;base64,"+imageBase64);
        map.put("codeIdentifier",codeIdentifier);
        return ResponseResult.ok(map);
    }

    @Override
    public ResponseResult<UserVo> login(String username, String password, String captcha, String codeIdentifier) {
        //从redis里拿到验证码,并且判断验证码是否正确
        String code= redisTemplate.opsForValue().get("captcha:codeKey" + codeIdentifier);
        if(code!=null&& !captcha.equals(code)){
            throw new ArithmeticException("验证码输入不正确");
        }
        // 如果正确,通过用户名查找用户尝试登录
        User user= Db.lambdaQuery(User.class).eq(User::getUsername, username).one();
        if(user==null){
            throw new RuntimeException("该用户不存在");
        }

        //最后把属性拷贝到userVO
        UserVo userVo= BeanUtil.copyProperties(user, UserVo.class);
        userVo.setToken(JWTUtils.createJWT(user.getId(),user.getPassword()));
        return ResponseResult.ok(userVo);
    }

    @Override
    public void register(String username, String password, String captcha, String codeIdentifier) {
        //从redis里拿到验证码,并且判断验证码是否正确
        String code= redisTemplate.opsForValue().get("captcha:codeKey" + codeIdentifier);
        if(code!=null&& !captcha.equals(code)){
            throw new ArithmeticException("验证码输入不正确");
        }
        // 使用非对称加密SM2进行加密
        SM2 sm2= SmUtil.sm2();
        byte[] encrypt= sm2.encrypt(password.getBytes(), KeyType.PublicKey);
        // 如果正确,尝试去新增用户
        User user= new User();
        user.setUsername(username);
        user.setPassword(Base64.encode(encrypt));
        user.setToken("11111111");
        save(user);
    }

    @Override
    public ResponseResult<List<OrderVo>> getOrderInfo() {
        LocalDate now= LocalDate.now();
        LocalDate startLocalDate= now.with(DayOfWeek.MONDAY);
        LocalDate endLocaldate= now.with(DayOfWeek.SUNDAY).plusDays(1);
        LocalDateTime startLocalDateTime= startLocalDate.atStartOfDay();
        LocalDateTime endLocalDateTime= endLocaldate.atStartOfDay();

        // 获取订单信息数量
        List<OrderVo> orderInfo= baseMapper.getOrderInfo(startLocalDateTime,endLocalDateTime);
        return ResponseResult.ok(orderInfo);
    }
}
