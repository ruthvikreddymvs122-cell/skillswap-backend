package com.skillswap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.Skill;
import com.skillswap.repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // CREATE SKILL
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // GET ALL SKILLS
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // GET SKILL BY ID
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    // UPDATE SKILL
    public Skill updateSkill(Long id, Skill skill) {

        Skill existingSkill = skillRepository.findById(id).orElse(null);

        if (existingSkill == null) {
            return null;
        }

        existingSkill.setName(skill.getName());
        existingSkill.setCategory(skill.getCategory());
        existingSkill.setDescription(skill.getDescription());

        return skillRepository.save(existingSkill);
    }

    // DELETE SKILL
    public boolean deleteSkill(Long id) {

        if (!skillRepository.existsById(id)) {
            return false;
        }

        skillRepository.deleteById(id);
        return true;
    }

    // FIND SKILL BY NAME
    public Skill findByName(String name) {
        return skillRepository.findByNameIgnoreCase(name).orElse(null);
    }

    // CHECK SKILL EXISTS
    public boolean existsByName(String name) {
        return skillRepository.existsByNameIgnoreCase(name);
    }

    // SEARCH SKILLS BY NAME
    public List<Skill> searchByName(String name) {
        return skillRepository.findByNameContainingIgnoreCase(name);
    }

    // GET SKILLS BY CATEGORY
    public List<Skill> getByCategory(String category) {
        return skillRepository.findByCategoryIgnoreCase(category);
    }
}