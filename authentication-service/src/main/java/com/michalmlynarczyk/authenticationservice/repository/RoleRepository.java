package com.michalmlynarczyk.authenticationservice.repository;

import com.michalmlynarczyk.authenticationservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(final String name);
}
