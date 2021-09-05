package com.cybertek.businessmansystem_api.util;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {

    @Value("${Security.jwt.secret-key}")
    private String secret = "cybertek";

    private String  createToken(Map<String,Object> claims, String username){
        return null;
    }
    private Claims extractAllClaims(String token){

        return null;
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver){

        return null;
    }

    private String extractUsername(String token){

        return null;
    }





    }
