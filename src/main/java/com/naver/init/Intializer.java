package com.naver.init;

import com.naver.entity.Episode;
import com.naver.entity.HashTag;
import com.naver.entity.Member;
import com.naver.entity.PublishingDay;
import com.naver.entity.ReadEpisode;
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
import com.naver.repository.ReadEpisodeRepository;
import com.naver.repository.WebtoonHashTagRepository;
import com.naver.repository.WebtoonPublishingDayRepository;
import com.naver.repository.WebtoonRepository;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class Intializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final HashTagRepository hashTagRepository;
    private final WebtoonRepository webtoonRepository;
    private final EpisodeRepository episodeRepository;
    private final WebtoonHashTagRepository webtoonHashTagRepository;
    private final WebtoonPublishingDayRepository webtoonPublishingDayRepository;
    private final PublishingDayRepository publishingDayRepository;
    private final WebtoonAboutGenerator webtoonAboutGenerator;
    private final ReadEpisodeRepository readEpisodeRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        deleteDepulicatedWebtoon(800, 200);
    }

    /**
     * 중복 등록된 웹툰 에피소드 삭제
     */
    private void deleteDepulicatedWebtoon(int startIdx, int cnt) {
        List<Webtoon> webtoons = webtoonRepository.findAll();

        for (int i = startIdx; i < startIdx + cnt; i++) {
            Webtoon w = webtoons.get(i);

            List<Episode> episodePerWebtoons = episodeRepository.findByWebtoon(w).stream()
                    .sorted(Comparator.comparingLong(Episode::getEpisodeId))
                    .toList();

            Episode episode = episodePerWebtoons.get(0);
            List<Episode> dup = null;
            for (int j = 1; j < episodePerWebtoons.size(); ++j) {
                if (episode.getTitle().equals(episodePerWebtoons.get(j).getTitle())) {
                    dup = episodePerWebtoons.subList(j, episodePerWebtoons.size());
                    break;
                }
            }

            int size = 0;
            if (dup != null) {
                size = dup.size();
//              readEpisodeRepository.deleteReadEpisodesByEpisodeIn(dup);
//				episodeRepository.deleteAllInBatch(dup);
            }
            log.info("{} {} {}", w.getWebtoonId(), episodePerWebtoons.size(), size);
        }
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
    @Transactional
    public void generateRandomReadWebtoon() {
        for (Long i = 1L; i <= 1000L; ++i) {
            List<ReadEpisode> readEpisodes = webtoonAboutGenerator.generateRandomReadWebtoon(i);
            System.out.println("bulk insert start: " + readEpisodes.size());

            long start = System.nanoTime();
            readEpisodeRepository.saveAll(readEpisodes);
            System.out.println(System.nanoTime() - start + "ns");
        }
    }

	@Transactional
	public void generateRandomReadWebtoon(Long webtoonId) {
			List<ReadEpisode> readEpisodes = webtoonAboutGenerator.generateRandomReadWebtoon(webtoonId);
			System.out.println("bulk insert start: " + readEpisodes.size());

			long start = System.nanoTime();
			readEpisodeRepository.saveAll(readEpisodes);
			System.out.println(System.nanoTime() - start + "ns");
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

            webtoonPublishingDays.add(WebtoonPublishingDay.builder().publishingDay(publishingDay).webtoon(w)
                    .createdAt(w.getCreatedAt()).updatedAt(w.getCreatedAt()).build());
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
                webtoonHashTagList.add(WebtoonHashTag.builder().webtoon(w).hashtag(hashTag).createdAt(createdAt)
                        .updatedAt(createdAt).build());
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
            while (episodeCreatedAt.isBefore(finishedWebtoonEndTime)) {
                episodeList.add(Episode.builder().title(title + " " + cnt + "화").webtoon(w).createdAt(episodeCreatedAt)
                        .updatedAt(episodeCreatedAt).viewCount(0).build());
                ++cnt;
                episodeCreatedAt = episodeCreatedAt.plusDays(7);
            }

            episodeRepository.saveAll(episodeList);
        }
    }

    private void saveEpisode(Long webtoonId) {
        Webtoon w = webtoonRepository.findById(webtoonId).orElseThrow(IllegalStateException::new);

        String title = w.getTitle();
        LocalDateTime episodeCreatedAt = w.getCreatedAt();

        List<Episode> episodeList = new ArrayList<>();
        int cnt = 1;
        LocalDateTime finishedWebtoonEndTime = w.getUpdatedAt();
        while (episodeCreatedAt.isBefore(finishedWebtoonEndTime)) {
            episodeList.add(Episode.builder().title(title + " " + cnt + "화").webtoon(w).createdAt(episodeCreatedAt)
                    .updatedAt(episodeCreatedAt).viewCount(0).build());
            ++cnt;
            episodeCreatedAt = episodeCreatedAt.plusDays(7);
			log.info("episode: {}", cnt);
        }

        episodeRepository.saveAll(episodeList);
    }

    private void saveMember() {
        List<Member> members = MemberGenerator.generateMember(10000);
        memberRepository.saveAll(members);
    }

    private void saveHashTag() {
        List<HashTag> hashTagList = new ArrayList<>();

        hashTagList
                .add(new HashTag("#로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#액션", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#일상", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#스릴러", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#개그", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#무협/사극", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#감성", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#스포츠", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#연도별웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#브랜드웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(new HashTag("#드라마&영화 원작웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0),
                LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#먼치킨", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#학원로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#로판", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#게임판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#해외작품", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#법정드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#범죄", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#인플루언서", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#고자극로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#아이돌", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#이세계", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#동양풍판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList
                .add(new HashTag("#빙의", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#소년왕도물", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));

        hashTagRepository.saveAll(hashTagList);
    }
}
