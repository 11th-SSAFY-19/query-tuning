package com.naver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.naver.entity.PublishingDay;

public interface PublishingDayRepository extends JpaRepository<PublishingDay, Long> {
	@Query("select p "
		+ "from PublishingDay p "
		+ "where p.day = :day")
	PublishingDay findByDay(@Param("day") String day);
}
