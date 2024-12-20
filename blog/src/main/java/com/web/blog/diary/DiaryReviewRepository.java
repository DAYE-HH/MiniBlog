package com.web.blog.diary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryReviewRepository extends JpaRepository<DiaryReview, Integer>{

	Optional<DiaryReview> findById(Long id);

}
