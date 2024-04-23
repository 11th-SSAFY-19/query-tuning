package com.naver.repository;

import com.naver.entity.WebtoonPublishingDay;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naver.entity.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
<<<<<<< HEAD
=======

    List<Webtoon> findAllByPublicationStatus(String status);
>>>>>>> 67ca5c210be50ad8e28c9fbb1083d3a9e79470e0
}
