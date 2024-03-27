package com.naver.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "WebtoonPublishingDay")
public class WebtoonPublishingDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webtoon_publishing_day_id")
	private Long webtoonPublishingDayId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publishing_day_id")
	private PublishingDay publishingDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_id")
	private Webtoon webtoon;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
