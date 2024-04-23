package com.naver.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpisodeLikeServiceTest {
	private final EpisodeLikeService episodeLikeService;

	@Autowired
	EpisodeLikeServiceTest(EpisodeLikeService episodeLikeService) {
		this.episodeLikeService = episodeLikeService;
	}

	@Test
	void createEpisodeLike() {
		episodeLikeService.createEpisodeLike();
	}
}
