package com.web.blog.diary;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;

@Service
public class DiaryReviewService {
	@Autowired
	private DiaryReviewRepository diaryReviewRepository;
	
	public void create(Diary diary, String cotent) {
		DiaryReview diaryReview = new DiaryReview();
		diaryReview.setContent(cotent);
		diaryReview.setCreateDate(LocalDateTime.now());
		diaryReview.setDiary(diary);
		
		this.diaryReviewRepository.save(diaryReview);
	}
	public DiaryReview selectOneDiaryReview(Long id) {
		Optional<DiaryReview> oDiaryReview = this.diaryReviewRepository.findById(id);
		if(oDiaryReview.isPresent()) {
			return oDiaryReview.get();
		}
		throw new DataNotFoundException("review not found");
	}
	public void delete(DiaryReview diaryReview) {
		this.diaryReviewRepository.delete(diaryReview);
	}
	public void modify(DiaryReview diaryReview) {
		this.diaryReviewRepository.save(diaryReview);
	}
}
