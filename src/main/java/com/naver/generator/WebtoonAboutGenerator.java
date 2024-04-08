package com.naver.generator;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.entity.Episode;
import com.naver.entity.InterestedWebtoon;
import com.naver.entity.Member;
import com.naver.entity.ReadEpisode;
import com.naver.entity.Webtoon;
import com.naver.repository.EpisodeRepository;
import com.naver.repository.InterestedWebtoonRepository;
import com.naver.repository.ReadEpisodeRepository;
import com.naver.repository.WebtoonRepository;

@Service
public class WebtoonAboutGenerator {
	private final MemberGenerator memberGenerator;
	private final WebtoonRepository webtoonRepository;
	private final ReadEpisodeRepository readEpisodeRepository;
	private final EpisodeRepository episodeRepository;
	private final InterestedWebtoonRepository interestedWebtoonRepository;

	@Autowired
	public WebtoonAboutGenerator(MemberGenerator memberGenerator, WebtoonRepository webtoonRepository,
		ReadEpisodeRepository readEpisodeRepository, EpisodeRepository episodeRepository,
		InterestedWebtoonRepository interestedWebtoonRepository) {
		this.memberGenerator = memberGenerator;
		this.webtoonRepository = webtoonRepository;
		this.readEpisodeRepository = readEpisodeRepository;
		this.episodeRepository = episodeRepository;
		this.interestedWebtoonRepository = interestedWebtoonRepository;
	}

	public void generateRandomReadWebtoon() {
		List<Webtoon> all = webtoonRepository.findAll();
		// 웹툰에 대한 에피소드 뽑아내기
		for (Webtoon webtoon : all) {
			System.out.println("webtoon id: " + webtoon.getId());
			List<Episode> episodes = episodeRepository.findByWebtoon(webtoon);
			int minPeople = RandomGenerator.generateRandomNumber(20, 100);
//			int minPeople = RandomGenerator.generateRandomNumber(2, 15);
//			int minPeople = RandomGenerator.generateRandomNumber(1, 5);

			for (Episode episode : episodes) {
				int plus = RandomGenerator.generateRandomNumber(0, 100);
				List<Member> members = memberGenerator.randomMembers(minPeople + plus);
				for (Member member : members) {
					LocalDateTime createdAt = episode.getCreatedAt();
					LocalDateTime updatedAt = RandomGenerator.generateLocalDateTime(createdAt,
						createdAt.plusDays(100));
					ReadEpisode readEpisode = ReadEpisode.builder()
						.member(member)
						.episode(episode)
						.createdAt(createdAt)
						.updatedAt(updatedAt)
						.build();
					readEpisodeRepository.save(readEpisode);
				}
			}
		}
	}

	public void generateRandom() {
		List<Webtoon> all = webtoonRepository.findAll();
		int minPeople = RandomGenerator.generateRandomNumber(20, 150);
//		int minPeople = RandomGenerator.generateRandomNumber(1, 5);
		// 웹툰에 대한 에피소드 뽑아내기
		for (Webtoon webtoon : all) {
			System.out.println("webtoon: " + webtoon.getWebtoonId());
			int plus = RandomGenerator.generateRandomNumber(0, 100);
			List<Member> members = memberGenerator.randomMembers(minPeople + plus);
			for (Member member : members) {
				LocalDateTime createdAt = webtoon.getCreatedAt();
				LocalDateTime updatedAt = RandomGenerator.generateLocalDateTime(createdAt,
					createdAt.plusDays(100));
				InterestedWebtoon interestedWebtoon = InterestedWebtoon
					.builder()
					.member(member)
					.webtoon(webtoon)
					.createdAt(createdAt)
					.updatedAt(updatedAt)
					.build();
				interestedWebtoonRepository.save(interestedWebtoon);
			}
		}
	}
}
