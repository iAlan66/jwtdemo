package org.alan.jwtdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.alan.jwtdemo.utils.UserThreadLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Hello {

    @GetMapping("/hello")
    public String hello() {
        log.info(UserThreadLocal.getUser().getUsername());
        return "hello";
    }
}
