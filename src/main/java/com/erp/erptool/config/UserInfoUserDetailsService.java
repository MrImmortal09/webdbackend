package com.erp.erptool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.erp.erptool.users.models.UsersModel;
import com.erp.erptool.users.repository.UsersRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel userInfo = repository.findByUserName(username);
        if (userInfo == null) {
            System.out.println("No user found");
            throw new UsernameNotFoundException("No user found!");
        }
        return userInfo;
    }
}