package com.naver.generator;

import com.naver.entity.Comment;
import com.naver.entity.Episode;
import com.naver.entity.EpisodeLike;
import com.naver.entity.Member;
import com.naver.repository.EpisodeLikeRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class ReadBasedGenerator {

    private final EpisodeLikeRepostory episodeLikeRepostory;
    private final MemberGenerator memberGenerator;

    @Autowired ReadBasedGenerator(EpisodeLikeRepostory episodeLikeRepostory, MemberGenerator memberGenerator) {

        this.episodeLikeRepostory = episodeLikeRepostory;
        this.memberGenerator = memberGenerator;
    }

    public void saveLike(Episode episode) {
        Random random = new Random();
        // 좋아요 수
        int limit = 4;
        int n = random.nextInt(limit)+1;

        // 해당 에피소드를 본 유저 중에서 랜덤으로 좋아요할 사람 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(n, episode.getEpisodeId());

        for(int i = 0; i < n; i++){
            // 읽은 날짜보다 늦게 createdDate 만들기
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(episode.getCreatedAt(), episode.getCreatedAt().plus(100, ChronoUnit.DAYS));

            episodeLikeRepostory.save(new EpisodeLike(members.get(i), episode, createdDate, createdDate));
        }
    }
}

