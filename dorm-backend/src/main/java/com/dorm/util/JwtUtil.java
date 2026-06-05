//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secret:dormSystemSecretKey2026MustBeLongEnoughForHS256}")
    private String secret;
    @Value("${jwt.expire:86400000}")
    private Long expire;

    public JwtUtil() {
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Integer adminId, String username, Integer role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + this.expire);
        return Jwts.builder().subject(String.valueOf(adminId)).claim("username", username).claim("role", role).issuedAt(now).expiration(expiration).signWith(this.getKey(), SIG.HS256).compact();
    }

    public Claims parseToken(String token) {
        return (Claims)Jwts.parser().verifyWith(this.getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        try {
            this.parseToken(token);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public Integer getAdminIdFromToken(String token) {
        Claims claims = this.parseToken(token);
        return Integer.valueOf(claims.getSubject());
    }
}
