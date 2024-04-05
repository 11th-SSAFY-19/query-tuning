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
@Table(name = "ReadEpisode")
public class ReadEpisode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "read_episode_id")
	private Long readEpisodeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "episode_id")
	private Episode episode;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public ReadEpisode(Long readEpisodeId, Member member, Episode episode,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.readEpisodeId = readEpisodeId;
		this.member = member;
		this.episode = episode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
