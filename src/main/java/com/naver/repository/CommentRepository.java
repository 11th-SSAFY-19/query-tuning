package com.naver.repository;

import com.naver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select c.commentId " +
            "from Comment c")
    public List<Long> findAllCommentIds();


}
