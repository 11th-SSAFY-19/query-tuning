package com.naver.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "Author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "name")
	private String name;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public Author(Long authorId, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.authorId = authorId;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
