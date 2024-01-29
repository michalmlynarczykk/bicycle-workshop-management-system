package com.michalmlynarczyk.authenticationservice.service;

import com.michalmlynarczyk.authenticationservice.model.entity.User;
import com.michalmlynarczyk.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public User loadUserByUsername(final String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username = %s not found", username)));
    }
}
