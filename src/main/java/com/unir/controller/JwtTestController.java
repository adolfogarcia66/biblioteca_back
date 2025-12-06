package com.unir.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
public class JwtTestController {

    // Expiración en ms, por ejemplo 1 hora
    private final long jwtExpiration = 3600000;

    @GetMapping("/api/test/generate-jwt")
    public Map<String, String> generateJwt() {
        // 1️⃣ Genera un secret key seguro de 512 bits
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 2️⃣ Genera token con payload mínimo
        String token = Jwts.builder()
                .setSubject("test-user")           // puedes poner el ID o nombre que quieras
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // 3️⃣ Convierte la clave a Base64 para guardarla o reutilizarla
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        return Map.of(
                "token", token,
                "secretKeyBase64", base64Key
        );
    }
}