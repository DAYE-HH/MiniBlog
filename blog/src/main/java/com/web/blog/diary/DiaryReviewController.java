package com.web.blog.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@RequestMapping("/diaryReview")
@Controller
public class DiaryReviewController {
	@Autowired
	private DiaryReviewService diaryReviewService;
	
	@Autowired
	private DiaryService diaryService;
	
	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") Long id,
			@Valid DiaryReviewForm diaryReviewForm, BindingResult bindingResult) {
		Diary diary = this.diaryService.getOneDiary(null);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("diary",diary);
			return "diary_detail";
		}
		this.diaryReviewService.create(diary, diaryReviewForm.getContent());
		return "redirect:/diary/detail/" + id;
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		DiaryReview diaryReview = this.diaryReviewService.selectOneDiaryReview(id);
		this.diaryReviewService.delete(diaryReview);
		return "redirect:/diary/detail/" + diaryReview.getDiary().getId();
	}
	@GetMapping("/modify/{id}")
	public String modify(DiaryReviewForm diaryReviewForm, @PathVariable("id") Long id) {
		DiaryReview diaryReview = this.diaryReviewService.selectOneDiaryReview(id);
		diaryReviewForm.setContent(diaryReview.getContent());
		return "diaryreview_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid DiaryReviewForm diaryReviewForm, BindingResult bindingResult, 
			@PathVariable("id") Long id) {
		if(bindingResult.hasErrors()) {
			return "diaryreview_form";
		}
		DiaryReview diaryReview = this.diaryReviewService.selectOneDiaryReview(id);
		diaryReview.setContent(diaryReviewForm.getContent());
		this.diaryReviewService.modify(diaryReview);
		return "redirect:/diary/detail/" + diaryReview.getDiary().getId();
	}

}
