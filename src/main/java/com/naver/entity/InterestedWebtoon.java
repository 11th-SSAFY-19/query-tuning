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

import lombok.Builder;

@Entity
@Table(name = "InterestedWebtoon")
public class InterestedWebtoon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interested_webtoon_id")
	private Long interestedWebtoonId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_id")
	private Webtoon webtoon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public InterestedWebtoon(Long interestedWebtoonId, Webtoon webtoon, Member member, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.interestedWebtoonId = interestedWebtoonId;
		this.webtoon = webtoon;
		this.member = member;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
