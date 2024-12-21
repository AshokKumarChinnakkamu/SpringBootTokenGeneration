package com.example.demo.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTgenerater {

    public static String secretKey="";

    public JWTgenerater() {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keygen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static  String generateToken(String username){
        HashMap<String,Object> claims=new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+60*60*1800)).signWith(geyKey()).compact();
        //return "Here is the sample token : 6958df+65fa65f+6a5sd+985ad+65a+s6d58+6a5sd65a4sd654as65d46a54d68474dsa4dasccsd6d5g46dg5gh4hgddss54hg";
    }

    private static Key geyKey() {
        byte[] byt= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(byt);
    }


    public String extractUserName(String token) {
        return "";
    }
}
