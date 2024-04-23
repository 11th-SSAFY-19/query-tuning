package com.naver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.entity.WebtoonPublishingDay;

public interface WebtoonPublishingDayRepository extends JpaRepository<WebtoonPublishingDay, Long> {
}
