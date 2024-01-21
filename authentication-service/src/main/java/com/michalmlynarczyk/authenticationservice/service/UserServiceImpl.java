package com.michalmlynarczyk.authenticationservice.service;

import com.michalmlynarczyk.authenticationservice.exception.RoleNotFoundException;
import com.michalmlynarczyk.authenticationservice.exception.UserAlreadyExistsException;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;
import com.michalmlynarczyk.authenticationservice.model.entity.Role;
import com.michalmlynarczyk.authenticationservice.model.entity.User;
import com.michalmlynarczyk.authenticationservice.repository.RoleRepository;
import com.michalmlynarczyk.authenticationservice.repository.UserRepository;
import com.michalmlynarczyk.authenticationservice.util.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenGenerator jwtTokenGenerator;


    @Override
    public JwtTokenResponse login(final UserLoginRequest request) {
        log.trace("login() - enter - request = {}", request);
        final String email = request.email();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.password()));
        final var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with email = %s wasn't found", email)));

        log.trace("login() - exit - user = {}", user);
        return getJwtTokenResponse(user);
    }


    private JwtTokenResponse getJwtTokenResponse(final User user) {
        var token = jwtTokenGenerator.generateToken(user);
        return new JwtTokenResponse(token);
    }


    @Override
    public JwtTokenResponse register(final UserRegistrationRequest request) {
        log.trace("register() - enter - request = {}", request);
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with email  = {0} already exists", user.getEmail());
                });
        final String roleName = request.workshopPosition().getCandidateRole();
        final Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role = {0} wasn't found", roleName));

        final var user = User.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                Set.of(role),
                request.firstName(),
                request.lastName());

        userRepository.save(user);
        log.debug("register() - exit - user = {}", user);
        return getJwtTokenResponse(user);
    }
}
