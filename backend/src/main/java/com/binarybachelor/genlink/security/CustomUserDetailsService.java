package com.binarybachelor.genlink.security;

import com.binarybachelor.genlink.entity.UserEntity;
import com.binarybachelor.genlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByMobile(mobile)
                .orElseThrow(() -> new UsernameNotFoundException("Mobile Number not found with Mobile: " + mobile));
        return new CustomUserDetails(user);
    }
}
