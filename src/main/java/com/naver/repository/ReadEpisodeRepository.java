package com.naver.repository;

import com.naver.entity.Episode;
import com.naver.entity.Member;
import com.naver.entity.ReadEpisode;
import com.naver.entity.Webtoon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReadEpisodeRepository extends JpaRepository<ReadEpisode, Long> {

    @Query("SELECT r.member FROM ReadEpisode r WHERE r.episode.episodeId = :episodeId")
    List<Member> findByEpisode(@Param("episodeId") Long episodeId);

    void deleteReadEpisodesByEpisodeIn(List<Episode> episodes);
}
