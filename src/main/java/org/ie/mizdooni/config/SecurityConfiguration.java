package org.ie.mizdooni.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration { // TODO : remove additionals
        // bind the filters which we defined

        private static final String[] WHITE_LIST_URL = { "/", "/home", "/auth***", "/resources/**", "/VAADIN/**" };
        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final LogoutHandler logoutHandler;
        private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

        @Bean
        public CorsConfigurationSource corsConfiguration() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.cors(c -> c.configurationSource(corsConfiguration())).csrf(AbstractHttpConfigurer::disable)
                                .exceptionHandling(customizer -> customizer
                                                .authenticationEntryPoint(userAuthenticationEntryPoint))
                                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL).permitAll()
                                                .anyRequest().authenticated())// other ones should be authenticated
                                .formLogin(formLogin -> formLogin.loginPage("/auth")
                                                .loginProcessingUrl("/api/auth/login").defaultSuccessUrl("/", true))
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                // .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                                .logout(logout -> logout.logoutUrl("/api/auth/logout").addLogoutHandler(logoutHandler)
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()))
                                .requiresChannel(channel -> channel.anyRequest().requiresSecure());

                return http.build();
        }
}
