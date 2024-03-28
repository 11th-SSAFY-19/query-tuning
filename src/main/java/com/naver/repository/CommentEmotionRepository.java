package com.naver.repository;

import com.naver.entity.CommentEmotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentEmotionRepository extends JpaRepository<CommentEmotion, Long> {

    Long countByCommentId(long commentId);

}
