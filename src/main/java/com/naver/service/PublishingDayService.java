package com.naver.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.entity.PublishingDay;
import com.naver.repository.PublishingDayRepository;

@Service
public class PublishingDayService {
	private final PublishingDayRepository publishingDayRepository;

	@Autowired
	public PublishingDayService(PublishingDayRepository publishingDayRepository) {
		this.publishingDayRepository = publishingDayRepository;
	}

	@Transactional
	public void createPublishingDay() {
		List<PublishingDay> publishingDays = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();

		// 월요일부터 일요일까지 순회하면서 객체 생성
		publishingDays.add(PublishingDay.builder().day("MONDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("TUESDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("WEDNESDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("THURSDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("FRIDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("SATURDAY").createdAt(now).updatedAt(now).build());
		publishingDays.add(PublishingDay.builder().day("SUNDAY").createdAt(now).updatedAt(now).build());

		publishingDayRepository.saveAll(publishingDays);
	}
}
