package com.in17.springboot.blogrestapi.springsecurityjwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.in17.springboot.blogrestapi.userinfo.User;
import com.in17.springboot.blogrestapi.userinfo.UserRepository;

@Component
public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByName(username);
        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	    Optional<User> userOptional = repository.findByName(username);
//	    
//	    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//	    
//	    return new UserInfoDetails();
//	}

}
