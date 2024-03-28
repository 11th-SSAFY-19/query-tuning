package com.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.naver.entity.Author;
import com.naver.entity.Webtoon;

public class FileService {

	private void readWebtoonCsv() {
		String csvFile = "webtoon.csv";
		String line = "";
		String cvsSplitBy = ",";

		List<Webtoon> webtoons = new ArrayList<>();
		List<Author> authors = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new java.io.FileReader(csvFile))) {
			// CSV 파일의 각 라인을 읽어들임
			while ((line = br.readLine()) != null) {
				// 쉼표를 기준으로 라인을 분할
				String[] data = line.split(cvsSplitBy);

				// 각 필드에 해당하는 값을 추출
				int titleId = Integer.parseInt(data[0]);
				String titleName = data[1];
				String authorName = data[2];
				String thumbnailUrl = data[3];
				boolean isAdult = Boolean.parseBoolean(data[8]);
				double starScore = Double.parseDouble(data[9]);
				int viewCount = Integer.parseInt(data[10]);

				Author author = Author.builder()
					.name(authorName)
					.
					.build();
				// Webtoon 객체를 생성하여 리스트에 추가
				Webtoon webtoon = Webtoon.builder()
					.title(titleName)
					.build();
				webtoons.add(webtoon);
			}

			// 파싱된 데이터 확인
			for (Webtoon webtoon : webtoons) {
				System.out.println(webtoon);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
}
