package org.alan.jwtdemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.alan.jwtdemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    public final static String secret = "123456";

    //根据用户信息创建Jwt令牌
    public static String createJwt(User user) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        //过期时间为7天
        calendar.add(Calendar.SECOND, 3600 * 24 * 7);
        return JWT.create()
                //配置JWT自定义信息
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(calendar.getTime())  //设置过期时间
                .withIssuedAt(now)    //设置创建时间
                .sign(Algorithm.HMAC256(secret));   //最终签名
    }

    //根据Jwt验证并解析用户信息
    public static User resolveJwt(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT verify = jwtVerifier.verify(token);  //对JWT令牌进行验证，看看是否被修改
            Map<String, Claim> claims = verify.getClaims();  //获取令牌中内容

            User user = new User();
            user.setId(claims.get("id").asInt());
            user.setUsername(claims.get("username").asString());

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
