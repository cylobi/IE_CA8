package org.ie.mizdooni.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.ie.mizdooni.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @Value("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")
  private String googleSecret;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String getGoogleUsername(String jwt){
    var allClaims = Jwts.parserBuilder().setSigningKey(getGoogleKey()).build().parseClaimsJws(jwt).getBody();
    var username = allClaims.getSubject();
    return username;
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }// TODO remove

  public String generateTokenForUser(UserModel userDetails) {
    return generateTokenForUser(new HashMap<String, Object>(), userDetails);
  }

  public String generateTokenForUser(Map<String, Object> extraClaims, UserModel userDetails) {
    return buildTokenForUser(extraClaims, userDetails, jwtExpiration);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public String generateRefreshToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  public String generateRefreshTokenForUser(UserModel userDetails) {
    return buildTokenForUser(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
  }

  private String buildTokenForUser(Map<String, Object> extraClaims, UserModel user, long expiration) {
    return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuer("mizdooni")
        .setId(UUID.randomUUID().toString())// session management security
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Key getGoogleKey(){
    byte[] keyBytes = googleSecret.getBytes();
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
