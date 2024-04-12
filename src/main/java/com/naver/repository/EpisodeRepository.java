package com.naver.repository;

import java.util.List;

import com.naver.entity.Episode;
import com.naver.entity.Webtoon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
	@Query("select e from Episode e where e.webtoon =:webtoon")
	List<Episode> findByWebtoon(@Param("webtoon") Webtoon webtoon);
}
