package org.alan.jwtdemo.service;

import org.alan.jwtdemo.domain.dto.LoginUserDto;
import org.alan.jwtdemo.domain.dto.RegistryUserDto;
import org.alan.jwtdemo.entity.User;

public interface UserService {
    void registry(RegistryUserDto registryUserDto);

    String login(LoginUserDto loginUserDto);

    User checkToken(String token);
}
