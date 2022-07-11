package com.kafka.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kafka.chat.security.JwtAccessDeniedHandler;
import com.kafka.chat.security.JwtAuthenticationEntryPoint;
import com.kafka.chat.security.JwtAuthenticationFilter;
import com.kafka.chat.security.JwtAuthenticationProvider;
import com.kafka.chat.security.LoginFailureHandler;
import com.kafka.chat.security.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
		private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
		private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
		private final LoginSuccessHandler loginSuccessHandler;
		private final LoginFailureHandler loginFailureHandler;
//		private final JwtAuthenticationProvider jwtAuthenticationProvider;
		private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                
                .and()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                
//                .and()
//                .authenticationProvider(jwtAuthenticationProvider)
                
                .and()
                .authorizeRequests()
                .antMatchers("/user/**", "/test")
                .permitAll()
                .anyRequest()
                .authenticated()
                
                
                .and()
                .formLogin()
                .loginProcessingUrl("/user/sign-in")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()
                ;
                
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
