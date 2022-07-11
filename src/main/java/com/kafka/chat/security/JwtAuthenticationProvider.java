package com.kafka.chat.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kafka.chat.domain.User;
import com.kafka.chat.repository.UserDBRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
	
		private final UserDBRepository userRepo;
		private final BCryptPasswordEncoder passwordEncoder;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    		log.debug(authentication.toString());
    		
    		String id = authentication.getName();
    		String password = (String)authentication.getCredentials();
    		
    		User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
    		
    		if(!passwordEncoder.matches(password, user.getPassword())) {
        		throw new BadCredentialsException("Password not matched");
    		}
    		
    		
    		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
    	return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
