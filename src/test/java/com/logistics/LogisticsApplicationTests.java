package com.logistics;

import com.logistics.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogisticsApplicationTests {

    @Test
    void contextLoads() {
        String token= JWTUtils.createJWT("1111", "2222");
        System.out.println(JWTUtils.parseJWT(token));
    }

}
