package com.naver.generator;

import com.naver.entity.Episode;
import com.naver.entity.EpisodeLike;
import com.naver.entity.Member;
import com.naver.entity.Rate;
import com.naver.repository.EpisodeLikeRepostory;
import com.naver.repository.RateRepository;
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
    private final RateRepository rateRepository;

    @Autowired ReadBasedGenerator(EpisodeLikeRepostory episodeLikeRepostory, MemberGenerator memberGenerator, RateRepository rateRepository) {

        this.episodeLikeRepostory = episodeLikeRepostory;
        this.memberGenerator = memberGenerator;
        this.rateRepository = rateRepository;
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

    public void saveRate(Episode episode) {
        Random random = new Random();
        // 별점 넣은 수
        int limit = 10;
        int n = random.nextInt(limit)+1;

        // 해당 에피소드를 본 유저 중에서 랜덤으로 좋아요할 사람 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(n, episode.getEpisodeId());

        for(int i = 0; i < n; i++){
            // 읽은 날짜보다 늦게 createdDate 만들기
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(episode.getCreatedAt(), episode.getCreatedAt().plus(100, ChronoUnit.DAYS));
            int score = generateRandomRate();

            rateRepository.save(new Rate(score, members.get(i), episode, createdDate, createdDate));
        }
    }


    public static int generateRandomRate() {
        Random random = new Random();
        double probability = random.nextDouble(); // 0.0부터 1.0까지의 난수 생성

        if (probability < 0.99) {
            // 4부터 5까지의 난수 생성
            return 4 + random.nextInt(2);
        } else {
            // 0부터 3까지의 범위에서 정수 난수 생성
            return random.nextInt(4);
        }
    }
}

