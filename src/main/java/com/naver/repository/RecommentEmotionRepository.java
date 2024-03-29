package com.naver.repository;

import com.naver.entity.Recomment;
import com.naver.entity.RecommentEmotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommentEmotionRepository extends JpaRepository<RecommentEmotion, Long> {
}
