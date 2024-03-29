package com.naver.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebtoonGeneratorTest {
	private final WebtoonGenerator webtoonGenerator;

	@Autowired
	WebtoonGeneratorTest(WebtoonGenerator webtoonGenerator) {
		this.webtoonGenerator = webtoonGenerator;
	}

	@Test
	public void createRandomFinishedWebtoon() {
		webtoonGenerator.createRandomWebtoon();
	}

}
