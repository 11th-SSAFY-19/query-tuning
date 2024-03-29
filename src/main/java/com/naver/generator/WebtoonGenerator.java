package com.naver.generator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		List<Webtoon> webtoons = new ArrayList<>();
		for (int i = 0; i < 352; i++) {
			String title = "웹툰" + i;
			boolean noYouth = false;
			if (i % 30 == 0) {
				noYouth = true;
			}
			LocalDateTime updatedDay = RandomGenerator
				.generateLocalDateTime(LocalDateTime.now().minusYears(6L), LocalDateTime.now().minusYears(1L));
			long random = RandomGenerator.generateRandomNumber(1, 6);
			Webtoon webtoon = Webtoon.builder()
				.title(title)
				.publicationStatus(FINISH)
				.noYouth(noYouth)
				.updatedAt(updatedDay)
				.createdAt(updatedDay.minusYears(random))
				.build();
			webtoons.add(webtoon);
		}


		for (int i = 352; i < 1352; i++) {
			String title = "웹툰" +  i;
			boolean noYouth = false;
			String status = ING;

			LocalDateTime updatedDay = RandomGenerator
				.generateLocalDateTime(LocalDateTime.now().minusDays(7L), LocalDateTime.now());
			if (i % 30 == 0) {
				noYouth = true;
			}
			if (i % 20 == 0) {
				status = FINISH;
				updatedDay = RandomGenerator
					.generateLocalDateTime(LocalDateTime.now().minusYears(7L), LocalDateTime.now().minusYears(1L));
			}
			long random = RandomGenerator.generateRandomNumber(3, 8);
			Webtoon webtoon = Webtoon.builder()
				.title(title)
				.publicationStatus(status)
				.noYouth(noYouth)
				.updatedAt(updatedDay)
				.createdAt(updatedDay.minusYears(random))
				.build();
			webtoons.add(webtoon);
		}
		System.out.println(webtoons.size());
		webtoonRepository.saveAll(webtoons);
	}


}
