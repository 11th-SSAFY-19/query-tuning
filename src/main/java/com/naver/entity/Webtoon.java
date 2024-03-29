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
@Table(name = "Webtoon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webtoon_id")
	private Long webtoonId;

	@Column(name = "title")
	private String title;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "description")
	private String description;

	@Column(name = "publication_status")
	private String publicationStatus;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "noYouth", columnDefinition = "TINYINT(1)")
	private boolean noYouth;

	@Builder
	public Webtoon(Long webtoonId, String title, String thumbnail, String description, String publicationStatus,
		LocalDateTime createdAt, LocalDateTime updatedAt, boolean noYouth) {
		this.webtoonId = webtoonId;
		this.title = title;
		this.thumbnail = thumbnail;
		this.description = description;
		this.publicationStatus = publicationStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.noYouth = noYouth;
	}
}
