package com.example.twotteur.repositories;

import com.example.twotteur.models.ERole;
import com.example.twotteur.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
