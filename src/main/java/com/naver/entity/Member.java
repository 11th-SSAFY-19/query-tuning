package com.naver.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "Member")
@Getter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "password")
	private String password;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "age")
	private Integer age;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Member(String loginId, String password, String nickname, Integer age, LocalDateTime createdAt,
				  LocalDateTime updatedAt) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Member{" +
				"memberId=" + memberId +
				", loginId='" + loginId + '\'' +
				", password='" + password + '\'' +
				", nickname='" + nickname + '\'' +
				", age=" + age +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
