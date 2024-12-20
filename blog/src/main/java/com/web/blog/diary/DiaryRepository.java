package com.web.blog.diary;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.diary.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
//	
//	Diary findbySubject (String subject);
//	Diary findBySubjectAndContent (String subject, String content);
//	
//	List<Diary> findBySubjectLike(String subject);
	Page<Diary> findAll(Pageable pageable);
	Page<Diary> findAll(Specification<Diary> sf, Pageable pageable);
	}
