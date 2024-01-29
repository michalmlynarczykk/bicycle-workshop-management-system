package com.michalmlynarczyk.authenticationservice.service;

import com.michalmlynarczyk.authenticationservice.exception.RoleNotFoundException;
import com.michalmlynarczyk.authenticationservice.exception.UserAlreadyExistsException;
import com.michalmlynarczyk.authenticationservice.exception.UserNotFoundException;
import com.michalmlynarczyk.authenticationservice.model.WorkshopPosition;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;
import com.michalmlynarczyk.authenticationservice.model.dto.response.UserDetailsResponse;
import com.michalmlynarczyk.authenticationservice.model.entity.Role;
import com.michalmlynarczyk.authenticationservice.model.entity.User;
import com.michalmlynarczyk.authenticationservice.repository.RoleRepository;
import com.michalmlynarczyk.authenticationservice.repository.UserRepository;
import com.michalmlynarczyk.authenticationservice.util.jwt.JwtTokenGenerator;
import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
        final User user = userRepository.findByEmail(email)
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

        final User user = User.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                Set.of(role),
                request.firstName(),
                request.lastName());

        userRepository.save(user);
        log.debug("register() - exit - user = {}", user);
        return getJwtTokenResponse(user);
    }


    @Override
    @Transactional
    public void assignWorkshopAndUpgradeRole(final UUID userId, final UUID workshopId) {
        log.trace("assignWorkshopAndUpgradeRole() - enter - userId = {}, workshopId = {}", userId, workshopId);
        final User user = getUserOrThrowException(userId);
        user.setWorkshopId(workshopId);
        user.setWorkshopAssignedAt(OffsetDateTime.now());
        upgradeUserRoles(user);

        log.debug("assignWorkshopAndUpgradeRole() - exit - user = {}", user);
    }


    @Override
    public UserDetailsResponse getUserDetails(final UUID userId, final CustomAuthenticationPrincipal principal) {
        log.trace("getUserDetails() - enter - userId = {}, principal = {}", userId, principal);
        final User user = getUserOrThrowException(userId);

        final UUID userWorkshopId = user.getWorkshopId();
        if (userWorkshopId != null && !userWorkshopId.equals(principal.workshopId())) {
            throw new UserNotFoundException("User with id = {0} wasn't found", userId);
        }
        return UserMapper.toDto(user);
    }


    private User getUserOrThrowException(final UUID userId) {
        log.trace("getUserOrThrowException() - enter - userId = {}", userId);
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = {0} wasn't found", userId));
        log.debug("getUserOrThrowException() - exit - user = {}", user);
        return user;
    }


    private void upgradeUserRoles(final User user) {
        log.trace("upgradeUserRoles() - enter - user = {}", user);
        final Set<Role> roles = user.getRoles();
        final List<WorkshopPosition> userWorkshopPositions = user.getRoleNames()
                .stream()
                .map(WorkshopPosition::getByCandidateRole)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        log.debug("upgradeUserRoles() - userWorkshopPositions = {} - roles = {}", userWorkshopPositions, roles);


        userWorkshopPositions.stream()
                .map(WorkshopPosition::getCandidateRole)
                .forEach(candidateRole -> roles.removeIf(role -> role.getName().equals(candidateRole)));

        userWorkshopPositions.stream()
                .map(WorkshopPosition::getFullyQualifiedRole)
                .map(roleRepository::findByName)
                .forEach(roleOptional -> {
                    final Role role = roleOptional.orElseThrow(() ->
                            new RoleNotFoundException("Role not found - missing configuration"));
                    roles.add(role);
                });
        log.trace("upgradeUserRoles() - exit - roles after upgrade = {}", roles);
    }


}
