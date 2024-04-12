package com.naver.generator;

import com.naver.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
	private final MemberRepository memberRepository;
	private final WebtoonRepository webtoonRepository;
	private final ReadEpisodeRepository readEpisodeRepository;
	private final EpisodeRepository episodeRepository;
	private final InterestedWebtoonRepository interestedWebtoonRepository;

	@Autowired
	public WebtoonAboutGenerator(MemberGenerator memberGenerator, MemberRepository memberRepository,
			WebtoonRepository webtoonRepository, ReadEpisodeRepository readEpisodeRepository,
			EpisodeRepository episodeRepository, InterestedWebtoonRepository interestedWebtoonRepository) {
		this.memberGenerator = memberGenerator;
		this.memberRepository = memberRepository;
		this.webtoonRepository = webtoonRepository;
		this.readEpisodeRepository = readEpisodeRepository;
		this.episodeRepository = episodeRepository;
		this.interestedWebtoonRepository = interestedWebtoonRepository;
	}

	public List<ReadEpisode> generateRandomReadWebtoon(Long webtoonId) {
		Webtoon webtoon = webtoonRepository.findById(webtoonId).orElseThrow(IllegalStateException::new);
		List<Member> memberList = memberRepository.findAll();

		List<ReadEpisode> rv = new ArrayList<>();
		System.out.println("webtoon ID: " + webtoon.getWebtoonId());
		List<Episode> episodes = episodeRepository.findByWebtoon(webtoon);
		int minPeople = RandomGenerator.generateRandomNumber(20, 100);

		for (Episode episode : episodes) {
			int plus = RandomGenerator.generateRandomNumber(0, 50);

			Collections.shuffle(memberList);
			int memberLen = minPeople + plus;
			for (int i = 0; i < memberLen; i++) {
				LocalDateTime createdAt = episode.getCreatedAt();
				LocalDateTime updatedAt = RandomGenerator.generateLocalDateTime(createdAt, createdAt.plusDays(100));
				ReadEpisode readEpisode = ReadEpisode.builder().member(memberList.get(i)).episode(episode)
						.createdAt(createdAt).updatedAt(updatedAt).build();
				rv.add(readEpisode);
			}
		}

		return rv;
	}

	public void generateRandom() {
		List<Webtoon> all = webtoonRepository.findAll();
		int minPeople = RandomGenerator.generateRandomNumber(20, 150);
		// 웹툰에 대한 에피소드 뽑아내기
		for (Webtoon webtoon : all) {
			System.out.println("webtoon: " + webtoon.getWebtoonId());
			int plus = RandomGenerator.generateRandomNumber(0, 100);
			List<Member> members = memberGenerator.randomMembers(minPeople + plus);
			for (Member member : members) {
				LocalDateTime createdAt = webtoon.getCreatedAt();
				LocalDateTime updatedAt = RandomGenerator.generateLocalDateTime(createdAt, createdAt.plusDays(100));
				InterestedWebtoon interestedWebtoon = InterestedWebtoon.builder().member(member).webtoon(webtoon)
						.createdAt(createdAt).updatedAt(updatedAt).build();
				interestedWebtoonRepository.save(interestedWebtoon);
			}
		}
	}
}
