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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WebtoonPublishingDay")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	@Builder
	public WebtoonPublishingDay(Long webtoonPublishingDayId, PublishingDay publishingDay, Webtoon webtoon,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.webtoonPublishingDayId = webtoonPublishingDayId;
		this.publishingDay = publishingDay;
		this.webtoon = webtoon;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
