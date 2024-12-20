package com.web.blog.reading;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/reading")
@RequiredArgsConstructor
@Controller
public class ReadingController {
	
	private final ReadingService readingService;
	
	@GetMapping("list")
	public String list(Model model) {
		List<Reading> readings = this.readingService.getAllReading();
		log.info(""+readings.size());
		for( Reading reading : readings ) {
			log.info(reading.toString());
		}
		model.addAttribute("readings", readings);
		return "reading_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Reading r = this.readingService.getOneReading(id);
		model.addAttribute("reading", r);
		return "reading_detail";
	}
	
	@GetMapping("/create")
	public String create(Model model, ReadingForm readingForm) {
		model.addAttribute("readingForm", readingForm);
		return "reading_form";
	}
	@PostMapping("/create")
	public String create(@Valid ReadingForm readingForm, BindingResult bindingResult) {
		// 게시글 등록이 안됐었는데 toString 추가
		System.out.println( readingForm.toString() );
		if( bindingResult.hasErrors() ) {
			return "reading_form";
		}
		this.readingService.create(readingForm.getSubject(), readingForm.getContent(), LocalDateTime.now());
		return "redirect:/reading/list";
	}
	@GetMapping("/modify/{id}")
	public String modify(Model model, ReadingForm readingForm, @PathVariable("id") Integer id) {
		Reading reading = this.readingService.getOneReading(id);
		readingForm.setSubject(reading.getSubject());
		readingForm.setContent(reading.getContent());
		model.addAttribute("readingForm", readingForm);
		return "reading_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid ReadingForm readingForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "reading_form";
		}
		Reading reading = this.readingService.getOneReading(id);
		reading.setSubject(readingForm.getSubject());
		reading.setContent(readingForm.getContent());
		this.readingService.modify(reading);
		return "redirect:/post/detail/" + id;
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Reading reading = this.readingService.getOneReading(id);
		this.readingService.delete(reading);
		return "redirect:/reading/list";
	}


	
}
