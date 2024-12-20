package com.web.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@RequestMapping("/postReview")
@Controller
public class PostReviewController {
	@Autowired
	private PostReviewService postReviewService;
	
	@Autowired
	private PostService postService;

	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") Integer id,
			@Valid PostReviewForm postReviewForm, BindingResult bindingResult) {
		Post post = this.postService.getOnePost(id);
		// 오류 검사 추가
		if( bindingResult.hasErrors() ) {
			// 원래 detatil에 접속하던 방식으로 접근(재현)
			model.addAttribute("post", post);
			return "post_detail";
		}
		this.postReviewService.create(post , postReviewForm.getContent());
		return "redirect:/post/detail/" + id;
}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		PostReview postReview = this.postReviewService.selectOnePostReview(id);
		this.postReviewService.delete(postReview);
		return "redirect:/post/detail/" + postReview.getPost().getId();
	}
	@GetMapping("/modify/{id}")
	public String modify(PostReviewForm postReviewForm, @PathVariable("id") Integer id) {
		PostReview postReview = this.postReviewService.selectOnePostReview(id);
		postReviewForm.setContent(postReview.getContent());
		return "postreview_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid PostReviewForm postReviewForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "postreview_form";
		}
		PostReview postReview = this.postReviewService.selectOnePostReview(id);
		postReview.setContent(postReviewForm.getContent());
		this.postReviewService.modify(postReview);
		return "redirect:/post/detail/" + postReview.getPost().getId();
	}
}
