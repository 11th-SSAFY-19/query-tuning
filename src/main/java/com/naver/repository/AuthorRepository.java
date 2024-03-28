package com.naver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
