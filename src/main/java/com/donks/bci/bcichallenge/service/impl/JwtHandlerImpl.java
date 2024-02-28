package com.donks.bci.bcichallenge.service.impl;

import com.donks.bci.bcichallenge.entity.UserEntity;
import com.donks.bci.bcichallenge.service.JwtHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtHandlerImpl implements JwtHandler {

    private static final long EXPIRATION_TIME = 864_000_000;

    @Value("${jwt.secret.public}")
    private String SECRET_KEY;

    public String createToken(UserEntity user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);


        Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());


        return Jwts.builder()
                .claim("userid", user.getId())
                .claim("username", user.getName())
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public boolean validToken(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UserEntity extractFromJwt(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
            return claims.getBody().get("user", UserEntity.class);
        } catch (Exception e) {
            return null;
        }
    }

}
