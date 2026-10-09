package com.skillswap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.User;
import com.skillswap.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE USER
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // UPDATE USER
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            return null;
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setSkills(user.getSkills());
        existingUser.setInterests(user.getInterests());

        return userRepository.save(existingUser);
    }

    // DELETE USER
    public boolean deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }

    // FIND USER BY EMAIL
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // CHECK EMAIL
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // FIND USERS BY SKILL
    public List<User> findBySkill(String skill) {
        return userRepository.findBySkillsContainingIgnoreCase(skill);
    }

    // FIND USERS BY INTEREST
    public List<User> findByInterest(String interest) {
        return userRepository.findByInterestsContainingIgnoreCase(interest);
    }
}