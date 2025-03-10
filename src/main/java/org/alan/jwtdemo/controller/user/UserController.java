package org.alan.jwtdemo.controller.user;

import org.alan.jwtdemo.domain.dto.LoginUserDto;
import org.alan.jwtdemo.domain.dto.RegistryUserDto;
import org.alan.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/registry")
    public String registry(@RequestBody RegistryUserDto registryUserDto) {
        userservice.registry(registryUserDto);
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto loginUserDto) {
        String token = userservice.login(loginUserDto);
        return token;
    }
}
