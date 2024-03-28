package com.naver;

import com.naver.generator.CommentGenerator;
import com.naver.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebtoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtoonApplication.class, args);
	}

}
