package com.github.seanv.gymtracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    /**
     * Spring injects values from application.properties using @Value annotation
     *
     * Actual key and expiration values will be stored as environment variables
     */

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secretKey;


    /**
     * Secret key will be stored as Base64 encoded String in environment variables. Though jwt needs a SecretKey object
     * - a cryptographic key type - not a plain String.
     *
     * getSigningKey() does 2 things:
     * 1. Decoders.BASE64.decode() - converts base64 back to raw bytes
     * 2.Keys.hmacShaKeyFor() - takes the bytes and builds a proper SecretKey object configured for HMAC-SHA signing
     */
    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * generateToken() is responsible for building actual token string.
     */
    public String generateToken(UserDetails userDetails){

        Date current = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(current)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * A Claim is a simple piece of information asserted about the user, stored in the jwt payload.
     *
     * Shares same concept as ID card (Name: Sean, DOB: 2000.03/18,...), though jwt would look like:
     * sub(subject):sean@gmail.com
     * iat(issuedAT): 1714000000
     * exp(expiration): 171400000
     * Plus any other custom claims you add
     *
     * Claims in jjwt is simply a Map that hold all these key-value pairs extracted from the JWT payload.
     * When you parse a token, jjwt reads the payload JSON and gives you back a Claims object you can query.
     *
     * There are standard claims defined by the JWT specification - sub,iar,exp,iss,aud - and you can add custom ones
     * you want, like roles and user id
     *
     * There Claims is simply a map of all data in JWT
     */

    /**
     * extractClaim() - generic method:
     *
     * Since Claims is a map, sometimes you only want specific data, not all of it as this requires writing a method
     * for every separate claim. So you use a generic method that accepts a Function that describes which claim to
     * extract.
     *
     * "Function<Claims, T>" means - give me a function that takes a Claims object and returns something of type T.
     * The caller decides what to extract.
     *
     * Example:
     *
     * //extracting the subject (username) - T becomes String
     * extractClaim(token, Claims::getSubject)
     *
     * // extracting expiration - T becomes Date
     * extractClaim(token, Claims::getExpiration)
     *
     * Claims::getSubject is a method reference - it's shorthand for "claims -> claims.getSubject()"
     * You are passing the method itself as the function
     */

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.
                parser()
                .verifyWith(getSigningKey()) // tells parser which key to use to verify the signature - recomputes signature
                .build()
                .parseSignedClaims(token)// Takes token string, splits into header/payload/signature, recomputes signature and compares them
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private  boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
