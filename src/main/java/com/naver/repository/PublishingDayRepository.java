package com.naver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.entity.PublishingDay;

public interface PublishingDayRepository extends JpaRepository<PublishingDay, Long> {
	PublishingDay findByDay(String day);
}
