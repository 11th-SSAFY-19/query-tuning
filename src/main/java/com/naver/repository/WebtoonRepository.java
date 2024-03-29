package com.naver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.entity.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
}
