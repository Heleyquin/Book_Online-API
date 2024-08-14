package com.DocSach.Sach.config;

import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Responsitory.AccountResponsi;
import com.DocSach.Sach.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AccountResponsi accountResponsi;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountResponsi.findByUsername(username);
        return new User(account.getUsername(), account.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_"+account.getRole())));
    }
}
