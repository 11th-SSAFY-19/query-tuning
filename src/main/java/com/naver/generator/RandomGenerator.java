package com.naver.generator;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
	public LocalDateTime generateLocalDateTime(LocalDateTime startDate, LocalDateTime endDate) {
		long startEpochSecond = startDate.toEpochSecond(java.time.ZoneOffset.UTC);
		long endEpochSecond = endDate.toEpochSecond(java.time.ZoneOffset.UTC);

		long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond);

		return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, java.time.ZoneOffset.UTC);
	}
}
