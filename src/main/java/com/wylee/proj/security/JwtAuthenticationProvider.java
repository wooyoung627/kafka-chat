package com.wylee.proj.security;

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

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserDBRepository;

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
    		
    		// 임시
    		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
    		
    		return new UsernamePasswordAuthenticationToken(user, password, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
//    		log.debug("@@ supports @@ " + authentication.toString());
    	return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
