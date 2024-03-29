package com.naver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileServiceTest {
	private final FileService fileService;

	@Autowired
	public FileServiceTest(FileService fileService) {
		this.fileService = fileService;
	}

	@Test
	public void readWebtoonCsv() {
		fileService.readWebtoonCsv();
	}
}
