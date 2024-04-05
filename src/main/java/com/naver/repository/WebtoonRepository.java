package com.naver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naver.entity.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
	@Query("select w from Webtoon w join fetch w.")
	List<Webtoon> findAllWithReadEpisode();
}
