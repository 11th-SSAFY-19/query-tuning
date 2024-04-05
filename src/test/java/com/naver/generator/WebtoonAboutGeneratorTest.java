package com.naver.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebtoonAboutGeneratorTest {
	private final WebtoonAboutGenerator webtoonAboutGenerator;

	@Autowired
	public WebtoonAboutGeneratorTest(WebtoonAboutGenerator webtoonAboutGenerator) {
		this.webtoonAboutGenerator = webtoonAboutGenerator;
	}

	@Test
	void generateRandomReadWebtoon() {
		webtoonAboutGenerator.generateRandomReadWebtoon();
	}

	@Test
	void generateRandom() {
		webtoonAboutGenerator.generateRandom();
	}
}
