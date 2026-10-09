package com.skillswap.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillswap.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find users by skill
    List<User> findBySkillsContainingIgnoreCase(String skill);

    // Find users by interest
    List<User> findByInterestsContainingIgnoreCase(String interest);
}