package com.naver.generator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
	public static LocalDateTime generateLocalDateTime(LocalDateTime startDate, LocalDateTime endDate) {
		long startEpochSecond = startDate.toEpochSecond(java.time.ZoneOffset.UTC);
		long endEpochSecond = endDate.toEpochSecond(java.time.ZoneOffset.UTC);

		long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond);

		return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, java.time.ZoneOffset.UTC);
	}

	// 리스트에서 중복되지 않게 N개의 요소를 랜덤으로 선택하는 메서드
	public static <T> List<T> selectNRandom(List<T> originalList, int n) {
		if (originalList == null || originalList.isEmpty() || n < 0 || n > originalList.size()) {
			throw new IllegalArgumentException("Invalid arguments");
		}

		List<T> resultList = new ArrayList<>();
		Random random = new Random();

		while (resultList.size() < n) {
			int randomIndex = random.nextInt(originalList.size());
			T element = originalList.get(randomIndex);
			if (!resultList.contains(element)) {
				resultList.add(element);
			}
		}

		return resultList;
	}
}
