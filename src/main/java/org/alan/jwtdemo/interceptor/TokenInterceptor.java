package org.alan.jwtdemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.alan.jwtdemo.entity.User;
import org.alan.jwtdemo.service.UserService;
import org.alan.jwtdemo.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userservice;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasLength(authorization)) {
            throw new RuntimeException("token为空");
        }
        String token = authorization.split(" ")[1];
        if (!StringUtils.hasLength(token)) {
            throw new RuntimeException("token错误");
        }
        User user = userservice.checkToken(token);
        if (user == null) {
            throw new RuntimeException("token错误");
        }
        UserThreadLocal.setUser(user);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        UserThreadLocal.removeUser();
    }
}
