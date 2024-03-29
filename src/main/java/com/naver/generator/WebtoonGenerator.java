package com.naver.generator;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.entity.Webtoon;
import com.naver.repository.WebtoonRepository;

@Service
public class WebtoonGenerator {

	private static final String FINISH = "완결";
	private static final String ING = "연재중";
	private final WebtoonRepository webtoonRepository;

	@Autowired
	public WebtoonGenerator(WebtoonRepository webtoonRepository) {
		this.webtoonRepository = webtoonRepository;
	}

	public void createRandomWebtoon() {

		for (int i = 0; i < 352; i++) {
			String title = "웹툰" + i;
			boolean noYouth = false;
			if (i % 30 == 0) {
				noYouth = true;
			}
			LocalDateTime updatedDay = RandomGenerator
				.generateLocalDateTime(LocalDateTime.now().minusYears(6L), LocalDateTime.now().minusYears(1L));
			long random = 3L;
			Webtoon webtoon = Webtoon.builder()
				.title(title)
				.publicationStatus(FINISH)
				.noYouth(noYouth)
				.updatedAt(updatedDay)
				.createdAt(updatedDay.minusYears(random))
				.build();

		}
	}

}
