package com.naver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HashTag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_id")
	private Long hashtagId;

	@Column(name = "name")
	private String name;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public HashTag(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
