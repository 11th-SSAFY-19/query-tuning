package com.naver.generator;

import com.naver.entity.Episode;
import com.naver.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CommentGeneratorTest {

    private final CommentGenerator commentGenerator;
    private final EpisodeRepository episodeRepository;
    private final ReadBasedGenerator readBasedGenerator;

    @Autowired
    public CommentGeneratorTest(CommentGenerator commentGenerator, EpisodeRepository episodeRepository, ReadBasedGenerator readBasedGenerator){
        this.commentGenerator = commentGenerator;
        this.episodeRepository = episodeRepository;
        this.readBasedGenerator = readBasedGenerator;
    }


    //@Test
    void saveComment(){
        Optional<Episode> episode = episodeRepository.findById(1L);
        if(!episode.isEmpty()){
            commentGenerator.saveComment(episode.get());
        }

    }

    @Test
    void saveLike() {
        Optional<Episode> episode = episodeRepository.findById(1L);
        if(!episode.isEmpty()){
            readBasedGenerator.saveLike(episode.get());
        }
    }


}
