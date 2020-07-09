package com.dalent.api.global.config.security;

import com.dalent.api.domain.auth.service.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.access.exp}")
    private int accessTokenExpiration;

    @Value("${auth.jwt.refresh.exp}")
    private int refreshTokenExpiration;

    @Value("${auth.jwt.http.header}")
    private String header;

    private final AuthDetailsService authDetailsService;

    @PostConstruct
    private void init() { secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); }

    public String generateAccessToken(String nickname) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .setSubject(nickname)
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String nickname) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .setSubject(nickname)
                .claim("type", "refresh_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(this.getUserNickname(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserNickname(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    String resolveToken(HttpServletRequest request) { return request.getHeader(this.header); };

    public boolean validateToken(String token, String type) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date()) && claims.getBody().get("type").equals(type);
        } catch (Exception e) {
            return false;
        }
    }
}
