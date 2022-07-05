package com.wylee.proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wylee.proj.security.JwtAccessDeniedHandler;
import com.wylee.proj.security.JwtAuthenticationEntryPoint;
import com.wylee.proj.security.JwtAuthenticationFilter;
import com.wylee.proj.security.JwtAuthenticationProvider;
import com.wylee.proj.security.LoginFailureHandler;
import com.wylee.proj.security.LoginSuccessHandler;

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
//                ;
                
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
