package org.peters3.usersecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //@Bean
    //public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    //    http
    //        .csrf(csrf -> csrf.disable())
    //        .authorizeHttpRequests(auth -> auth
    //            //.requestMatchers("/auth/register", "/auth/login").permitAll()
    //            // Cambia la línea de abajo temporalmente:
    //            // .anyRequest().authenticated() // <-- Línea original
    //            .requestMatchers("/auth/admin/**").permitAll()
    //            .anyRequest().authenticated() // < -- Mueve esta línea al final si agregas la anterior
    //        )
    //        .addFilterBefore(new JwtFilter(authManager, new JwtUtil(), userDetailsService()),
    //                         UsernamePasswordAuthenticationFilter.class);
//
    //    return http.build();
    //}
}