package com.naver.generator;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String EXTENDED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
	private static final SecureRandom random = new SecureRandom();

	private RandomGenerator() {
	}

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

	/**
	 * 영어 대소문자, 숫자를 사용하여 랜덤으로 문자열을 생성한다.
	 * @param minSize
	 * @param maxSize
	 * @return
	 */
	public static String generateString(int minSize, int maxSize) {
		int length = minSize + random.nextInt(maxSize - minSize + 1);
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();
	}

	/**
	 * 영어 대소문자, 숫자, 특수기호를 사용하여 랜덤으로 문자열을 생성한다.
	 * @param minSize
	 * @param maxSize
	 * @return
	 */
	public static String generateExtendedString(int minSize, int maxSize) {
		int length = minSize + random.nextInt(maxSize - minSize + 1);
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(EXTENDED_CHARACTERS.length());
			char randomChar = EXTENDED_CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();

	}

	/**
	 * min 이상 max 이하 숫자를 랜덤으로 선택하여 반환한다.
	 * @param min
	 * @param max
	 * @return min 이상 max 이하 숫자
	 */
	public static int generateRandomNumber(int min, int max) {
		return random.nextInt(max - min) + min;
	}
}
