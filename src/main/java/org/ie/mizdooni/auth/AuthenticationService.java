package org.ie.mizdooni.auth;

import org.ie.mizdooni.config.JwtService;
import org.ie.mizdooni.dao.UserDao;
import org.ie.mizdooni.model.UserModel;
import org.ie.mizdooni.serializer.LoginUserRequestBody;
import org.ie.mizdooni.token.Token;
import org.ie.mizdooni.token.TokenRepository;
import org.ie.mizdooni.token.TokenType;
import org.ie.mizdooni.token.UserToken;
import org.ie.mizdooni.token.UserTokenDao;
import org.ie.mizdooni.user.Role;
import org.ie.mizdooni.user.User;
import org.ie.mizdooni.user.UserRepository;
import org.ie.mizdooni.utils.exception.BaseWebappException;
import org.ie.mizdooni.utils.exception.UserNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final UserTokenDao tokenDao;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
        .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
  }

  public AuthenticationResponse authenticateUsingUserpass(LoginUserRequestBody request) throws BaseWebappException {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    var user = UserDao.getInstance().findOneByUsername(request.getUsername());
    if (user == null) {
      throw new UserNotFoundException();
    }
    var jwtToken = jwtService.generateTokenForUser(user);
    var refreshToken = jwtService.generateRefreshTokenForUser(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder().user(user).token(jwtToken).token_type(TokenType.BEARER).expired(false).revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void saveUserToken(UserModel user, String jwtToken) {
    var token = UserToken.builder().user(user).token(jwtToken).token_type(TokenType.BEARER).expired(false)
        .revoked(false).build();
    tokenDao.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  private void revokeAllUserTokens(UserModel user) {
    var validUserTokens = tokenDao.findAllValidTokenByUser(user.getUsername());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenDao.saveAll(validUserTokens);
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException, UserNotFoundException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      // var user = this.repository.findByEmail(userEmail).orElseThrow();
      var user = UserDao.getInstance().findOneByUsername(userEmail);
      if (user == null) {
        throw new UserNotFoundException();
      }
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
