package com.naver.generator;

import com.naver.entity.Comment;
import com.naver.entity.Recomment;
import com.naver.repository.CommentEmotionRepository;
import com.naver.repository.CommentRepository;
import com.naver.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentGeneratorTest {

    private final CommentGenerator commentGenerator;
    private final CommentRepository commentRepository;
    private final CommentEmotionRepository commentEmotionRepository;
    private final RecommentRepository recommentRepository;

    @Autowired
    public CommentGeneratorTest(CommentGenerator commentGenerator, CommentRepository commentRepository, CommentEmotionRepository commentEmotionRepository, RecommentRepository recommentRepository) {
        this.commentGenerator = commentGenerator;
        this.commentRepository = commentRepository;
        this.commentEmotionRepository = commentEmotionRepository;
        this.recommentRepository = recommentRepository;
    }

    //@Test
    void comment() {
        commentGenerator.comment();

    }

    //@Test
    void recomment() {
        commentGenerator.recomment();
    }

    //@Test
    void saveEmotion()  {
        Optional<Comment> comment = commentRepository.findById(1L);
        if(!comment.isEmpty()){
            commentGenerator.saveCommentEmotion(comment.get());
        }

    }

    @Test
    void saveRecommentEmotion()  {
        Optional<Recomment> recomment = recommentRepository.findById(1L);
        if(!recomment.isEmpty()){
            commentGenerator.saveRecommentEmotion(recomment.get());
        }
    }
}
