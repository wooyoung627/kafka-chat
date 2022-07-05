package com.wylee.proj.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import com.wylee.proj.entity.User;

import java.util.Date;

@Slf4j
public class JwtTokenProvider {

    private static final String JWT_SECRET = "1234secret5678key!@#$";
    
    private static final int REF_JWT_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7;
    private static final int ACC_JWT_EXPIRATION_MS = 1000 * 60 * 60 * 24;
    
    private static final String ACC_JWT_NAME = "acc";
    private static final String REF_JWT_NAME = "ref";
    
    public static String generateAccToken(User user) {
    	return generateToken(user, ACC_JWT_EXPIRATION_MS);
    }
    
    public static String generateRefToken(User user) {
    	return generateToken(user, REF_JWT_EXPIRATION_MS);
    }

    public static String generateToken(User user, int ms){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ms);
        return Jwts.builder()
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static String getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJwt(token)
                .getBody();

        return claims.getSubject();
    }

    	// 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
