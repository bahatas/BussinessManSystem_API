package com.cybertek.businessmansystem_api.service;

import com.cybertek.businessmansystem_api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


    User loadUser( String param);
}
