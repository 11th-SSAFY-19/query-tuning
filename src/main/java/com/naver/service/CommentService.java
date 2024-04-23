package com.naver.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.entity.Comment;
import com.naver.entity.Episode;
import com.naver.entity.Member;
import com.naver.entity.Webtoon;
import com.naver.generator.RandomGenerator;
import com.naver.repository.CommentRepository;
import com.naver.repository.EpisodeRepository;
import com.naver.repository.MemberRepository;
import com.naver.repository.WebtoonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final EpisodeRepository episodeRepository;
	private final MemberRepository memberRepository;

	@Transactional(readOnly = false)
	public void createComment() {
		Episode episode = episodeRepository.findById(1L)
			.orElseThrow();
		Member member = memberRepository.findById(1L)
			.orElseThrow();
		List<Comment> comments = new ArrayList<>();
		for (int i = 0; i < 50_000_000; i++) {
			Comment comment = new Comment("comment" + i, member, episode, LocalDateTime.now(), LocalDateTime.now());
			comments.add(comment);
		}
		commentRepository.saveAll(comments);
	}
}
