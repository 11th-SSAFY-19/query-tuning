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
@Table(name = "ReCommentEmotion")
public class ReCommentEmotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recomment_emotion_id")
	private Long recommentEmotionId;

	@Column(name = "emotion_status", columnDefinition = "TINYINT(1)")
	private Boolean emotionStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recomment_id")
	private ReComment reComment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// Getters and setters
}
