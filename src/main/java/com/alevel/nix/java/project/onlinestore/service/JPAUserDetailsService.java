package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JPAUserDetailsService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole());
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(simpleGrantedAuthority);
        return new User(
                user.getName(),
                user.getPassword(),
                list
        );
    }
}