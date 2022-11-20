package com.ecommerce.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.Entities.User;
import com.ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepo.findByUserEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "Email"+username, 0));
		return user;
	}

}
