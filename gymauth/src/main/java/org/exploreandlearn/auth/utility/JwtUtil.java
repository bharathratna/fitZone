package org.exploreandlearn.auth.utility;

import org.exploreandlearn.auth.entity.UserInfoDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.token.key}")
    private String jwtSecretKey;

    @Value("${jwt.token.expiration}")
    private int aliveTime;

    public String generateToken(UserInfoDetails userInfoDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userInfoDetails.getMemberId());
        claims.put("roles", userInfoDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        return createToken(claims, userInfoDetails.getUsername());
    }

    private  String createToken(Map<String, Object> claim, String username){
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(Date.from(date.toInstant().plus(30, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(jwtSecretKey.getBytes()))
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractClaim(String token){
        return extractAllClaims(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getRoles(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("roles").toString();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token){
        Claims claims = extractAllClaims(token);
        return  claims.getExpiration();
    }
}
