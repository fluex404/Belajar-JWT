package com.fluex404.belajarjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@SpringBootTest
class BelajarJwtApplicationTests {


    private final String SECRET_KEY = "mySecretK3y2J3L4J024JKJL2KKJ3240234LNADSMFASDFL234O2341I4JKAJSDLF0JALS244ASDFNA02";

    @Test
    void contextLoads() {
        System.out.println("It Work's!");
    }

    @Test
    void jwt_create(){
        String jwtToken = Jwts.builder()
//                .setIssuer("Isa")
//                .setSubject("I dunno what i'm doing")
                .claim("name", "Isa test")
                .claim("email", "isa@gmail.com")
//                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()*60*60))
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )
                .compact()
        ;
        System.out.println(jwtToken);


    }

    @Test
    void jwt_extract_then_not_valid(){
        try {
            String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSXNhIHRlc3RudGVzdCBlYXN0IiwiZW1haWwiOiJpc2FAZ21haWwuY29tIiwiZXhwIjo2MDY3NTkzNTcwNDc0fQ.Hfi2yma_W4Z7RHDQ2wethnlgFR9B2u5VIRvtaDMZW0c";
//            String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSXNhIHRlc3QiLCJlbWFpbCI6ImlzYUBnbWFpbC5jb20iLCJleHAiOjYwNjc1OTM4OTYyMDl9.62Gxl7c5u4jUdWTIVTEbrMDVOiQSaSR-c_DegCqEVLs";
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken);
            System.out.println(claimsJws.getSignature());
            System.out.println(claimsJws.getBody());
            System.out.println(claimsJws.getHeader());

            System.out.println("===== DATA FROM BODY OF JWT ======");
            System.out.println("email: "+claimsJws.getBody().get("email"));
            System.out.println("name: "+claimsJws.getBody().get("name"));
        }catch (SignatureException e){
            System.out.println("jwt tidak valid");
        }
    }
    @Test
    void jwt_extract_then_return(){
        try {
//            String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSXNhIHRlc3RudGVzdCBlYXN0IiwiZW1haWwiOiJpc2FAZ21haWwuY29tIiwiZXhwIjo2MDY3NTkzNTcwNDc0fQ.Hfi2yma_W4Z7RHDQ2wethnlgFR9B2u5VIRvtaDMZW0c";
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSXNhIHRlc3QiLCJlbWFpbCI6ImlzYUBnbWFpbC5jb20iLCJleHAiOjYwNjc1OTM4OTYyMDl9.62Gxl7c5u4jUdWTIVTEbrMDVOiQSaSR-c_DegCqEVLs";
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken);
            System.out.println(claimsJws.getSignature());
            System.out.println(claimsJws.getBody());
            System.out.println(claimsJws.getHeader());

            System.out.println("===== DATA FROM BODY OF JWT ======");
            System.out.println("email: "+claimsJws.getBody().get("email"));
            System.out.println("name: "+claimsJws.getBody().get("name"));
        }catch (SignatureException e){
            System.out.println("jwt tidak valid");
        }
    }

}
