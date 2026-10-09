package com.skillswap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillswap.entity.SkillType;
import com.skillswap.entity.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

    List<UserSkill> findByUserId(Long userId);

    List<UserSkill> findBySkillId(Long skillId);

    List<UserSkill> findByUserIdAndType(Long userId, SkillType type);

    List<UserSkill> findBySkillIdAndType(Long skillId, SkillType type);

    boolean existsByUserIdAndSkillIdAndType(
            Long userId,
            Long skillId,
            SkillType type
    );
}