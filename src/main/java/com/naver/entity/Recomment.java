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
@Table(name = "Recomment")
public class Recomment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recomment_id")
	private Long recommentId;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id")
	private Comment comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// Getters and setters

	public Recomment(String content, Comment comment, Member member, LocalDateTime createdAt, LocalDateTime updatedAt) {
		//this.recommentId = recommentId;
		this.content = content;
		this.comment = comment;
		this.member = member;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
