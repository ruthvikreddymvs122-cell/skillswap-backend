package com.skillswap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillswap.entity.UserSkill;
import com.skillswap.service.MatchingService;

@RestController
@RequestMapping("/api/matching")
@CrossOrigin(origins = "http://localhost:5175")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    // =========================
    // FIND MATCHING TEACHERS
    // GET /api/matching/teachers/{userId}
    // =========================
    @GetMapping("/teachers/{userId}")
    public ResponseEntity<List<UserSkill>> findMatchingTeachers(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                matchingService.findMatchingTeachers(userId)
        );
    }
}