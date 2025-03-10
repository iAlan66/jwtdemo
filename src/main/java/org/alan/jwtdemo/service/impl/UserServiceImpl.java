package org.alan.jwtdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.alan.jwtdemo.domain.dto.LoginUserDto;
import org.alan.jwtdemo.domain.dto.RegistryUserDto;
import org.alan.jwtdemo.entity.User;
import org.alan.jwtdemo.mapper.UserMapper;
import org.alan.jwtdemo.service.UserService;
import org.alan.jwtdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void registry(RegistryUserDto registryUserDto) {
        User user = new User();
        user.setUsername(registryUserDto.getUsername());
        String password = registryUserDto.getPassword();
        String salt = user.getSalt();
        String md5Password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        user.setPassword(md5Password);
        userMapper.insert(user);
    }

    @Override
    public String login(LoginUserDto loginUserDto) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, loginUserDto.getUsername());
        User user = userMapper.selectOne(lambdaQueryWrapper);

        String password = loginUserDto.getPassword();
        String salt = user.getSalt();
        String md5Password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        if (md5Password.equals(user.getPassword())) {
            return JwtUtil.createJwt(user);
        } else {
            throw new RuntimeException("密码错误");
        }
    }

    @Override
    public User checkToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        User user = JwtUtil.resolveJwt(token);
        return user;
    }
}
