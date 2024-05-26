package org.ie.mizdooni.config;

import org.ie.mizdooni.token.UserTokenDao;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final UserTokenDao tokenDao;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().contains("/api/v1/auth") || request.getServletPath().contains("/auth")
        || request.getServletPath().contains("/api/auth/")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      response.setStatus(401);
      response.getWriter().write("WRONG_AUTHENTICATION_HEADER_FORMAT");
      return;
    }
    try {
      jwt = authHeader.substring(7);
      username = jwtService.extractUsername(jwt);
      if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
        throw new JwtException("username has a problem");
      }

      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      var isTokenValid = tokenDao.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
      if (!isTokenValid) {
        throw new JwtException("token not found");
      }

      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
        return;
      }

    } catch (JwtException e) {
      response.setStatus(401);
      response.getWriter().write("INVALID_JWT");
      return;
    }

    response.setStatus(401);
    response.getWriter().write("AUTHENTICATION_ERROR");
  }
}
