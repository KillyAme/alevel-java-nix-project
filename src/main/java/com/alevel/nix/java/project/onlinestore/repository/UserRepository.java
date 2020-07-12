package com.alevel.nix.java.project.onlinestore.repository;

import com.alevel.nix.java.project.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);


    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
