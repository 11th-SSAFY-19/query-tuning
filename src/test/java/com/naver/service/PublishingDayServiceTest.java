package com.naver.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class PublishingDayServiceTest {
	private final PublishingDayService publishingDayService;

	@Autowired
	public PublishingDayServiceTest(PublishingDayService publishingDayService) {
		this.publishingDayService = publishingDayService;
	}

	@Test
	void createPublishingDay() {
		publishingDayService.createPublishingDay();
	}
}
