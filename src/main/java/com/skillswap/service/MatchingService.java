package com.skillswap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.UserSkill;
import com.skillswap.repository.UserSkillRepository;

@Service
public class MatchingService {

    private final UserSkillRepository userSkillRepository;

    public MatchingService(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    // =========================
    // FIND MATCHING TEACHERS
    // =========================
    public List<UserSkill> findMatchingTeachers(Long userId) {

        List<UserSkill> learningSkills =
                userSkillRepository.findByUserIdAndType(
                        userId,
                        com.skillswap.entity.SkillType.LEARN
                );

        List<UserSkill> matches = new ArrayList<>();

        for (UserSkill learningSkill : learningSkills) {

            Long skillId = learningSkill.getSkill().getId();

            List<UserSkill> teachers =
                    userSkillRepository.findBySkillIdAndType(
                            skillId,
                            com.skillswap.entity.SkillType.TEACH
                    );

            for (UserSkill teacher : teachers) {

                // Don't match the user with themselves
                if (!teacher.getUser().getId().equals(userId)) {
                    matches.add(teacher);
                }
            }
        }

        return matches;
    }
}