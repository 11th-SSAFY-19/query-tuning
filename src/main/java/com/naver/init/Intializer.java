package com.naver.init;

import com.naver.entity.Episode;
import com.naver.entity.HashTag;
import com.naver.entity.Member;
import com.naver.entity.PublishingDay;
import com.naver.entity.Webtoon;
import com.naver.entity.WebtoonHashTag;
import com.naver.entity.WebtoonPublishingDay;
import com.naver.generate.MemberGenerator;
import com.naver.generator.RandomGenerator;
import com.naver.generator.WebtoonAboutGenerator;
import com.naver.repository.EpisodeRepository;
import com.naver.repository.HashTagRepository;
import com.naver.repository.MemberRepository;
import com.naver.repository.PublishingDayRepository;
import com.naver.repository.WebtoonHashTagRepository;
import com.naver.repository.WebtoonPublishingDayRepository;
import com.naver.repository.WebtoonRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Intializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final HashTagRepository hashTagRepository;
    private final WebtoonRepository webtoonRepository;
    private final EpisodeRepository episodeRepository;
    private final WebtoonHashTagRepository webtoonHashTagRepository;
    private final WebtoonPublishingDayRepository webtoonPublishingDayRepository;
    private final PublishingDayRepository publishingDayRepository;
    private final WebtoonAboutGenerator webtoonAboutGenerator;

    public Intializer(MemberRepository memberRepository, HashTagRepository hashTagRepository,
                      WebtoonRepository webtoonRepository, EpisodeRepository episodeRepository,
                      WebtoonHashTagRepository webtoonHashTagRepository,
                      WebtoonPublishingDayRepository webtoonPublishingDayRepository,
                      PublishingDayRepository publishingDayRepository, WebtoonAboutGenerator webtoonAboutGenerator) {
        this.memberRepository = memberRepository;
        this.hashTagRepository = hashTagRepository;
        this.webtoonRepository = webtoonRepository;
        this.episodeRepository = episodeRepository;
        this.webtoonHashTagRepository = webtoonHashTagRepository;
        this.webtoonPublishingDayRepository = webtoonPublishingDayRepository;
        this.publishingDayRepository = publishingDayRepository;
        this.webtoonAboutGenerator = webtoonAboutGenerator;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	generateRandomReadWebtoon();
    }

    /**
     * 관심 웹툰 등록
     */
    private void generateRandom() {
        webtoonAboutGenerator.generateRandom();
    }

    /**
     * 본 웹툰 등록
     */
    private void generateRandomReadWebtoon() {
        webtoonAboutGenerator.generateRandomReadWebtoon();
    }

    /**
     * 완결 웹툰(수동 등록)의 연재 날짜 등록
     */
    private void saveWebtoonPublishingDay() {
        List<Webtoon> webtoons = webtoonRepository.findAllByPublicationStatus("완결");
        List<PublishingDay> publishingDays = publishingDayRepository.findAll();
        List<WebtoonPublishingDay> webtoonPublishingDays = new ArrayList<>();

        for (Webtoon w : webtoons) {
            Collections.shuffle(publishingDays);
            PublishingDay publishingDay = publishingDays.get(0);

            webtoonPublishingDays.add(WebtoonPublishingDay.builder()
                    .publishingDay(publishingDay)
                    .webtoon(w)
                    .createdAt(w.getCreatedAt())
                    .updatedAt(w.getCreatedAt())
                    .build());
        }

        webtoonPublishingDayRepository.saveAll(webtoonPublishingDays);
    }

    /**
     * 웹툰 해시태그 등록, 웹툰당 2개씩
     */
    private void saveWebtoonHashTag() {
        List<HashTag> hashTagList = hashTagRepository.findAll();
        List<Webtoon> webtoonList = webtoonRepository.findAll();
        List<WebtoonHashTag> webtoonHashTagList = new ArrayList<>();

        for (Webtoon w : webtoonList) {
            LocalDateTime createdAt = w.getCreatedAt();

            List<HashTag> webtoonHashTag = RandomGenerator.selectNRandom(hashTagList, 2);
            for (HashTag hashTag : webtoonHashTag) {
                webtoonHashTagList.add(WebtoonHashTag.builder()
                        .webtoon(w)
                        .hashtag(hashTag)
                        .createdAt(createdAt)
                        .updatedAt(createdAt)
                        .build());
            }
        }

        webtoonHashTagRepository.saveAll(webtoonHashTagList);
    }


    private void saveEpisode() {
        List<Webtoon> webtoonList = webtoonRepository.findAll();

        for (Webtoon w : webtoonList) {
            String title = w.getTitle();
            LocalDateTime episodeCreatedAt = w.getCreatedAt();

            List<Episode> episodeList = new ArrayList<>();
            int cnt = 1;
            LocalDateTime finishedWebtoonEndTime = w.getUpdatedAt();
            while(episodeCreatedAt.isBefore(finishedWebtoonEndTime)) {
                episodeList.add(Episode.builder()
                        .title(title + " " + cnt + "화")
                        .webtoon(w)
                        .createdAt(episodeCreatedAt)
                        .updatedAt(episodeCreatedAt)
                        .viewCount(0)
                        .build());
                ++cnt;
                episodeCreatedAt = episodeCreatedAt.plusDays(7);
            }

            episodeRepository.saveAll(episodeList);
        }
    }

    private void saveMember() {
        List<Member> members = MemberGenerator.generateMember(10000);
        memberRepository.saveAll(members);
    }

    private void saveHashTag() {
        List<HashTag> hashTagList = new ArrayList<>();

        hashTagList.add(
                new HashTag("#로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#액션", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#일상", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#스릴러", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#개그", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#무협/사극", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#감성", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#스포츠", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#연도별웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#브랜드웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(new HashTag("#드라마&영화 원작웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0),
                LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#먼치킨", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#학원로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#로판", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#게임판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#해외작품", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#법정드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#범죄", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#인플루언서", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#고자극로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#아이돌", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#이세계", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#동양풍판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#빙의", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#소년왕도물", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));

        hashTagRepository.saveAll(hashTagList);
    }
}
