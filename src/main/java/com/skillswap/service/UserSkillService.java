package com.skillswap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.SkillType;
import com.skillswap.entity.UserSkill;
import com.skillswap.repository.UserSkillRepository;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;

    public UserSkillService(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    // =========================
    // ASSIGN SKILL TO USER
    // =========================
    public UserSkill assignSkill(UserSkill userSkill) {

        boolean exists = userSkillRepository
                .existsByUserIdAndSkillIdAndType(
                        userSkill.getUser().getId(),
                        userSkill.getSkill().getId(),
                        userSkill.getType()
                );

        if (exists) {
            return null;
        }

        return userSkillRepository.save(userSkill);
    }

    // =========================
    // GET ALL USER-SKILLS
    // =========================
    public List<UserSkill> getAllUserSkills() {
        return userSkillRepository.findAll();
    }

    // =========================
    // GET USER'S ALL SKILLS
    // =========================
    public List<UserSkill> getSkillsByUser(Long userId) {
        return userSkillRepository.findByUserId(userId);
    }

    // =========================
    // GET USERS FOR A SKILL
    // =========================
    public List<UserSkill> getUsersBySkill(Long skillId) {
        return userSkillRepository.findBySkillId(skillId);
    }

    // =========================
    // GET SKILLS USER CAN TEACH
    // =========================
    public List<UserSkill> getTeachingSkills(Long userId) {
        return userSkillRepository.findByUserIdAndType(
                userId,
                SkillType.TEACH
        );
    }

    // =========================
    // GET SKILLS USER WANTS TO LEARN
    // =========================
    public List<UserSkill> getLearningSkills(Long userId) {
        return userSkillRepository.findByUserIdAndType(
                userId,
                SkillType.LEARN
        );
    }

    // =========================
    // GET USERS WHO TEACH A SKILL
    // =========================
    public List<UserSkill> getSkillTeachers(Long skillId) {
        return userSkillRepository.findBySkillIdAndType(
                skillId,
                SkillType.TEACH
        );
    }

    // =========================
    // GET USERS WHO WANT TO LEARN A SKILL
    // =========================
    public List<UserSkill> getSkillLearners(Long skillId) {
        return userSkillRepository.findBySkillIdAndType(
                skillId,
                SkillType.LEARN
        );
    }

    // =========================
    // DELETE USER-SKILL
    // =========================
    public boolean deleteUserSkill(Long id) {

        if (!userSkillRepository.existsById(id)) {
            return false;
        }

        userSkillRepository.deleteById(id);
        return true;
    }
}