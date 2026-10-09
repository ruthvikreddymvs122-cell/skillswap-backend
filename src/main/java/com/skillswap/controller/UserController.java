package com.skillswap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillswap.entity.User;
import com.skillswap.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5175")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // CREATE USER
    // POST /api/users
    // =========================
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    // =========================
    // GET ALL USERS
    // GET /api/users
    // =========================
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // =========================
    // GET USER BY ID
    // GET /api/users/{id}
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    // =========================
    // UPDATE USER
    // PUT /api/users/{id}
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser);
    }

    // =========================
    // DELETE USER
    // DELETE /api/users/{id}
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        boolean deleted = userService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // =========================
    // SEARCH USERS BY SKILL
    // GET /api/users/search/skill/{skill}
    // =========================
    @GetMapping("/search/skill/{skill}")
    public ResponseEntity<List<User>> searchBySkill(
            @PathVariable String skill) {

        return ResponseEntity.ok(
                userService.findBySkill(skill)
        );
    }

    // =========================
    // SEARCH USERS BY INTEREST
    // GET /api/users/search/interest/{interest}
    // =========================
    @GetMapping("/search/interest/{interest}")
    public ResponseEntity<List<User>> searchByInterest(
            @PathVariable String interest) {

        return ResponseEntity.ok(
                userService.findByInterest(interest)
        );
    }
}