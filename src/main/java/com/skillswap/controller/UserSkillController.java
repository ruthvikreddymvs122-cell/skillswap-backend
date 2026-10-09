package com.skillswap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillswap.entity.UserSkill;
import com.skillswap.service.UserSkillService;

@RestController
@RequestMapping("/api/user-skills")
@CrossOrigin(origins = "http://localhost:5175")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    // =========================
    // ASSIGN SKILL TO USER
    // POST /api/user-skills
    // =========================
    @PostMapping
    public ResponseEntity<UserSkill> assignSkill(
            @RequestBody UserSkill userSkill) {

        UserSkill created = userSkillService.assignSkill(userSkill);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(created);
    }

    // =========================
    // GET ALL USER-SKILLS
    // GET /api/user-skills
    // =========================
    @GetMapping
    public ResponseEntity<List<UserSkill>> getAllUserSkills() {

        return ResponseEntity.ok(
                userSkillService.getAllUserSkills()
        );
    }

    // =========================
    // GET USER'S SKILLS
    // GET /api/user-skills/user/{userId}
    // =========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSkill>> getSkillsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userSkillService.getSkillsByUser(userId)
        );
    }

    // =========================
    // GET USERS FOR A SKILL
    // GET /api/user-skills/skill/{skillId}
    // =========================
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<UserSkill>> getUsersBySkill(
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                userSkillService.getUsersBySkill(skillId)
        );
    }

    // =========================
    // GET TEACHING SKILLS
    // GET /api/user-skills/user/{userId}/teach
    // =========================
    @GetMapping("/user/{userId}/teach")
    public ResponseEntity<List<UserSkill>> getTeachingSkills(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userSkillService.getTeachingSkills(userId)
        );
    }

    // =========================
    // GET LEARNING SKILLS
    // GET /api/user-skills/user/{userId}/learn
    // =========================
    @GetMapping("/user/{userId}/learn")
    public ResponseEntity<List<UserSkill>> getLearningSkills(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userSkillService.getLearningSkills(userId)
        );
    }

    // =========================
    // GET SKILL TEACHERS
    // GET /api/user-skills/skill/{skillId}/teachers
    // =========================
    @GetMapping("/skill/{skillId}/teachers")
    public ResponseEntity<List<UserSkill>> getSkillTeachers(
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                userSkillService.getSkillTeachers(skillId)
        );
    }

    // =========================
    // GET SKILL LEARNERS
    // GET /api/user-skills/skill/{skillId}/learners
    // =========================
    @GetMapping("/skill/{skillId}/learners")
    public ResponseEntity<List<UserSkill>> getSkillLearners(
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                userSkillService.getSkillLearners(skillId)
        );
    }

    // =========================
    // DELETE USER-SKILL
    // DELETE /api/user-skills/{id}
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSkill(
            @PathVariable Long id) {

        boolean deleted = userSkillService.deleteUserSkill(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}