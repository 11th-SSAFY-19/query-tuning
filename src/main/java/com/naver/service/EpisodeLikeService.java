package com.naver.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.entity.Comment;
import com.naver.entity.Episode;
import com.naver.entity.EpisodeLike;
import com.naver.entity.Member;
import com.naver.generator.MemberGenerator;
import com.naver.generator.RandomGenerator;
import com.naver.repository.EpisodeLikeRepostory;
import com.naver.repository.EpisodeRepository;
import com.naver.repository.MemberRepository;
import com.sun.source.tree.BinaryTree;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpisodeLikeService {
	private final EpisodeRepository episodeRepository;
	private final EpisodeLikeRepostory episodeLikeRepostory;
	private final MemberRepository memberRepository;
	private final MemberGenerator memberGenerator;

	@Transactional(readOnly = false)
	public void createEpisodeLike() {
		List<Episode> episodes = episodeRepository.findAll();
		List<EpisodeLike> episodeLikes = new ArrayList<>();
		for (Episode episode : episodes) {
			int numberOfMember = RandomGenerator.generateRandomNumber(5, 300);
			List<Member> members = memberGenerator.randomMembers(numberOfMember);
			LocalDateTime createdAt = episode.getCreatedAt();
			for (Member member : members) {
				LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(createdAt, createdAt.plus(100, ChronoUnit.DAYS));
				EpisodeLike episodeLike = new EpisodeLike(member, episode, createdDate, createdDate);
				episodeLikes.add(episodeLike);
			}
		}
		episodeLikeRepostory.saveAll(episodeLikes);
	}
}
