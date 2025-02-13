package com.user.farmtocart.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtilService {
    private final String SECRET_KEY = "786553a8625832f58a5b6f49ce6953fa3d7af8b5b5f47c7238462f97b3fa343214c940592f47fcaaf53aa7d8574505a8118f6d0e50f9fb009b936fadda92994944252ebf8fd5e5ee3a4c69ef007622293f3fd6da167dca56b861dcb1b89fabf98aaf36edda6fa71ed1fdd9d9224695e11bcf60c81b5d73e3154e88bb7e78bf94ba5204a8d361ed1237a26dc544bfcfbaab4af1910fcd497f068e7ae96ee61a3b99954f22225c534bc9e575752737d00f9eced1efdea661c905126945bf0c3752c22762530d513d92937d2b1236364c56e3379d6bdc92e55696bc7be3c4f4146d5224b518361a1a2147807b6f2f32dcf41ab2ac15195eaff991a7af73595fe594";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
