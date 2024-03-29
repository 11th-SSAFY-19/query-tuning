package com.naver.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PublishingDay")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublishingDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publishing_day_id")
	private Long publishingDayId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "day")
	private String day;

	@Builder
	public PublishingDay(Long publishingDayId, LocalDateTime createdAt, LocalDateTime updatedAt, String day) {
		this.publishingDayId = publishingDayId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.day = day;
	}

	@Override
	public String toString() {
		return "PublishingDay{" +
			"publishingDayId=" + publishingDayId +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			", day='" + day + '\'' +
			'}';
	}
}
