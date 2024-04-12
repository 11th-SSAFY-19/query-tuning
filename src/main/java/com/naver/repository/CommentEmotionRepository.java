package com.naver.repository;

import com.naver.entity.CommentEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentEmotionRepository extends JpaRepository<CommentEmotion, Long> {

    @Query("SELECT COUNT(e) FROM CommentEmotion e WHERE e.comment.commentId = :commentId")
    int countByCommentId(@Param("commentId") long commentId);

}
