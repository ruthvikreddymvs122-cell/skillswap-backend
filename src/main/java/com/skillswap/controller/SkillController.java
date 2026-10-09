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

import com.skillswap.entity.Skill;
import com.skillswap.service.SkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5175")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // =========================
    // CREATE SKILL
    // POST /api/skills
    // =========================
    @PostMapping
    public ResponseEntity<Skill> createSkill(
            @RequestBody Skill skill) {

        Skill createdSkill = skillService.createSkill(skill);

        return ResponseEntity.ok(createdSkill);
    }

    // =========================
    // GET ALL SKILLS
    // GET /api/skills
    // =========================
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(
                skillService.getAllSkills()
        );
    }

    // =========================
    // GET SKILL BY ID
    // GET /api/skills/{id}
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(
            @PathVariable Long id) {

        Skill skill = skillService.getSkillById(id);

        if (skill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(skill);
    }

    // =========================
    // UPDATE SKILL
    // PUT /api/skills/{id}
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable Long id,
            @RequestBody Skill skill) {

        Skill updatedSkill = skillService.updateSkill(id, skill);

        if (updatedSkill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedSkill);
    }

    // =========================
    // DELETE SKILL
    // DELETE /api/skills/{id}
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long id) {

        boolean deleted = skillService.deleteSkill(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // =========================
    // FIND SKILL BY NAME
    // GET /api/skills/name/{name}
    // =========================
    @GetMapping("/name/{name}")
    public ResponseEntity<Skill> findByName(
            @PathVariable String name) {

        Skill skill = skillService.findByName(name);

        if (skill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(skill);
    }

    // =========================
    // SEARCH SKILLS
    // GET /api/skills/search/{name}
    // =========================
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Skill>> searchByName(
            @PathVariable String name) {

        return ResponseEntity.ok(
                skillService.searchByName(name)
        );
    }

    // =========================
    // GET BY CATEGORY
    // GET /api/skills/category/{category}
    // =========================
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Skill>> getByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                skillService.getByCategory(category)
        );
    }
}