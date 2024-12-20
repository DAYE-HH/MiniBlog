package com.web.blog.guestbook;

import java.time.LocalDateTime;
import java.util.List;

import com.web.blog.guestbook.GuestbookService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Controller
public class GuestbookController {
	
	private final GuestbookService guestbookService;
	
	@GetMapping("list")
	public String list(Model model) {
		List<Guestbook> guestbooks = this.guestbookService.getAllGuestbook();
		log.info(""+guestbooks.size());
		for( Guestbook guestbook : guestbooks ) {
			log.info(guestbook.toString());
		}
		model.addAttribute("guestbooks", guestbooks);
		return "guestbook_list";
	}
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Guestbook g = this.guestbookService.getOneGuestbook(id);
		model.addAttribute("guestbook", g);
		return "guestbook_detail";
	}
	@GetMapping("/create")
	public String create(Model model, GuestbookForm guestbookForm) {
		model.addAttribute("guestbookForm", guestbookForm);
		return "guestbook_form";
	}
	@PostMapping("/create")
	public String create(@Valid GuestbookForm guestbookForm, BindingResult bindingResult) {
		// 게시글 등록이 안됐었는데 toString 추가
		System.out.println( guestbookForm.toString() );
		if( bindingResult.hasErrors() ) {
			return "guestbook_form";
		}
		this.guestbookService.create(guestbookForm.getName(), guestbookForm.getContent(), LocalDateTime.now());
		return "redirect:/guestbook/list";
	}
	@GetMapping("/modify/{id}")
	public String modify(Model model, GuestbookForm guestbookForm, @PathVariable("id") Integer id) {
		Guestbook guestbook = this.guestbookService.getOneGuestbook(id);
		guestbookForm.setName(guestbook.getName());
		guestbookForm.setContent(guestbook.getContent());
		model.addAttribute("guestbookForm", guestbookForm);
		return "guestbook_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid GuestbookForm guestbookForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "guestbook_form";
		}
		Guestbook guestbook = this.guestbookService.getOneGuestbook(id);
		guestbook.setName(guestbookForm.getName());
		guestbook.setContent(guestbookForm.getContent());
		this.guestbookService.modify(guestbook);
		return "redirect:/guestbook/list";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Guestbook guestbook = this.guestbookService.getOneGuestbook(id);
		this.guestbookService.delete(guestbook);
		return "redirect:/guestbook/list";
	}
	
	}


