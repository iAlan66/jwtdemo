package org.alan.jwtdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.alan.jwtdemo.mapper")
public class JwtdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtdemoApplication.class, args);
    }

}
