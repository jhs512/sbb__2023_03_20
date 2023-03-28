package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/question/list")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/question/detail/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/style.css")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin -> formLogin
                                // GET
                                // 시큐리티에게 우리가 만든 로그인 페이지 url을 알려준다.
                                // 만약에 하지 않으면 기본 로그인 페이지 url은 /login 이다.
                                .loginPage("/user/login")
                                // POST
                                // 시큐리티에게 로그인 폼 처리 url을 알려준다.
                                .loginProcessingUrl("/user/login")
                                .defaultSuccessUrl("/")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
