package com.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.naver.entity.Webtoon;
import com.naver.entity.WebtoonPublishingDay;
import com.naver.generator.RandomGenerator;
import com.naver.repository.PublishingDayRepository;
import com.naver.repository.WebtoonPublishingDayRepository;
import com.naver.repository.WebtoonRepository;

@Service
public class FileService {
	private final RandomGenerator randomGenerator;
	private final WebtoonRepository webtoonRepository;
	private final PublishingDayRepository publishingDayRepository;
	private final WebtoonPublishingDayRepository webtoonPublishingDayRepository;

	public FileService(RandomGenerator randomGenerator,
		WebtoonRepository webtoonRepository, PublishingDayRepository publishingDayRepository,
		WebtoonPublishingDayRepository webtoonPublishingDayRepository) {
		this.randomGenerator = randomGenerator;
		this.webtoonRepository = webtoonRepository;
		this.publishingDayRepository = publishingDayRepository;
		this.webtoonPublishingDayRepository = webtoonPublishingDayRepository;
	}

	public void readWebtoonCsv() {
		String csvFile = "src/main/resources/webtoon.csv";
		String line = "";
		String cvsSplitBy = ",";

		List<Webtoon> webtoons = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new java.io.FileReader(csvFile))) {
			// CSV 파일의 각 라인을 읽어들임
			while ((line = br.readLine()) != null) {
				// 쉼표를 기준으로 라인을 분할
				String[] data = line.split(cvsSplitBy);

				String titleName = data[2];
				String thumbnailUrl = data[3];
				boolean isAdult = Boolean.parseBoolean(data[7]);
				boolean isFinished = Boolean.parseBoolean(data[13]);
				String day = data[15];
				String status = "";
				if (isFinished) {
					status = "완결";
				} else {
					status = "연재중";
				}

				System.out.println(titleName);
				LocalDateTime startDate
					= LocalDateTime.of(2018, 1, 1, 0, 0, 0);
				LocalDateTime endDate
					= LocalDateTime.of(2024, 3, 28, 0, 0, 0);

				LocalDateTime generatedTime = randomGenerator.generateLocalDateTime(startDate, endDate);
				LocalDateTime endGeneratedTime = randomGenerator.generateLocalDateTime(startDate, endDate);

				// Webtoon 객체를 생성하여 리스트에 추가
				generatedTime = randomGenerator.generateLocalDateTime(startDate, endDate);
				endGeneratedTime = randomGenerator.generateLocalDateTime(endDate.minusDays(7L), LocalDateTime.now());

				Webtoon webtoon = Webtoon.builder()
					.title(titleName)
					.noYouth(isAdult)
					.thumbnail(thumbnailUrl)
					.createdAt(generatedTime)
					.updatedAt(endGeneratedTime)
					.publicationStatus(status)
					.build();
				Webtoon savedWebtoon = webtoonRepository.save(webtoon);

				LocalDateTime now = LocalDateTime.now();
				WebtoonPublishingDay webtoonPublishingDay = WebtoonPublishingDay
					.builder()
					.publishingDay(publishingDayRepository.findByDay(day))
					.webtoon(savedWebtoon)
					.createdAt(now)
					.updatedAt(now)
					.build();
				webtoonPublishingDayRepository.save(webtoonPublishingDay);
			}

			// 파싱된 데이터 확인
			for (Webtoon webtoon : webtoons) {
				System.out.println(webtoon);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
