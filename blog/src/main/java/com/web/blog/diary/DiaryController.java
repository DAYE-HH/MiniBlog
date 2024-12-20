package com.web.blog.diary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.blog.diary.DiaryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequestMapping("/diary")
@RequiredArgsConstructor
@Controller
public class DiaryController {
	private final DiaryService diaryService;
	

//	@GetMapping("list")
////	@ResponseBody
//	public String list(Model model) {
//		List<Diary> diarys = this.diaryService.getAllDiary();
//		log.info(""+diarys.size());
//		for( Diary diary : diarys ) {
//			log.info( diary.toString() );
//		}
//		model.addAttribute("diarys", diarys);
//		return "diary_list";
//	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id,
			DiaryReviewForm diaryReviewForm) {
		Diary d = this.diaryService.getOneDiary(id);
		model.addAttribute("diary", d);
		return "diary_detail";
	}
	@GetMapping("/create")
	public String create(Model model, DiaryForm diaryForm) {
		// 글작성하는 창 th:field="*{diaryForm.subject}" / th:field="*{diaryForm.content}"
		// model.addAttribute("문자열", 가져올거)
		model.addAttribute("diaryForm", diaryForm);
		return "diary_form";
	}
	@PostMapping("/create")
	public String create(@Valid DiaryForm diaryForm, BindingResult bindingResult) {
		System.out.println(diaryForm.toString());
		if( bindingResult.hasErrors() ) {
			return "diary_form";
		}
		this.diaryService.create(diaryForm.getSubject(), diaryForm.getContent(), LocalDateTime.now());
		return "redirect:/diary/list";
	}
	@GetMapping("/modify/{id}")
	public String modify(Model model, DiaryForm diaryForm, @PathVariable("id") Long id) {
		Diary diary = this.diaryService.getOneDiary(id);
		diaryForm.setSubject(diary.getSubject());
		diaryForm.setContent(diary.getContent());
		model.addAttribute("diaryForm", diaryForm);
		return "diary_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid DiaryForm diaryForm, BindingResult bindingResult, @PathVariable("id") Long id) {
		if(bindingResult.hasErrors()) {
			return "diary_form";
		}
		Diary diary = this.diaryService.getOneDiary(id);
		diary.setSubject(diaryForm.getSubject());
		diary.setContent(diaryForm.getContent());
		this.diaryService.modify(diary);
		return "redirect:/diary/detail/" +id;
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Diary diary = this.diaryService.getOneDiary(id);
		this.diaryService.delete(diary);
		return "redirect:/diary/list";
	}
	
	@GetMapping("/list")
	public String list(Model model, 
			@RequestParam(value="page", defaultValue = "0") int page) {
		Page<Diary> paging= this.diaryService.getList(page);
		model.addAttribute("paging", paging);
		return "diary_list";
	}
	@GetMapping("/list2")
	public String list2(Model model, 
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="keyword", defaultValue = "") String keyword) {
		// 1. 특정 페이지(인자)에 해당되는 페이징 데이터 획득
		Page<Diary> paging= this.diaryService.getList2(page, keyword);
		// 2. html에 전달
		model.addAttribute("keyword", keyword);
		model.addAttribute("paging", paging);
		return "diary_list";
	}
	}

