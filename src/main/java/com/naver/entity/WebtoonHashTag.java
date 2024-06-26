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
import lombok.Builder.ObtainVia;

@Entity
@Table(name = "WebtoonHashTag")
public class WebtoonHashTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webtoon_hashtag_id")
	private Long webtoonHashtagId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hashtag_id")
	private HashTag hashtag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webtoon_id")
	private Webtoon webtoon;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	private WebtoonHashTag(Long webtoonHashtagId, HashTag hashtag, Webtoon webtoon, LocalDateTime createdAt,
						  LocalDateTime updatedAt) {
		this.webtoonHashtagId = webtoonHashtagId;
		this.hashtag = hashtag;
		this.webtoon = webtoon;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
