package com.naver.generator;

import com.naver.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentGeneratorTest {

    private final CommentGenerator commentGenerator;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentGeneratorTest(CommentGenerator commentGenerator, CommentRepository commentRepository) {
        this.commentGenerator = commentGenerator;
        this.commentRepository = commentRepository;
    }

    @Test
    void comment() {
        //commentGenerator.comment();

    }

    @Test
    void recomment() {
        commentGenerator.recomment();
    }
}