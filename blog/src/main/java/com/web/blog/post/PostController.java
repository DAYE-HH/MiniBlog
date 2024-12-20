package com.web.blog.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.blog.diary.DiaryForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {	
	
	private final PostService postService;
	
	@GetMapping("list")
	public String list(Model model) {
		List<Post> posts = this.postService.getAllPost();
		log.info(""+posts.size());
		for( Post post : posts ) {
			log.info(post.toString());
		}
		model.addAttribute("posts", posts);
		return "post_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, 
			PostReviewForm postReviewForm) {
		Post p = this.postService.getOnePost(id);
		model.addAttribute("post", p);
		return "post_detail";
	}
	
	@GetMapping("/create")
	public String create(Model model, PostForm postForm) {
		model.addAttribute("postForm", postForm);
		return "post_form";
	}
	@PostMapping("/create")
	public String create(@Valid PostForm postForm, BindingResult bindingResult) {
		// 게시글 등록이 안됐었는데 toString 추가
		System.out.println( postForm.toString() );
		if( bindingResult.hasErrors() ) {
			return "post_form";
		}
		this.postService.create(postForm.getName(), postForm.getSubject(), postForm.getContent(), LocalDateTime.now());
		return "redirect:/post/list";
	}
	@GetMapping("/modify/{id}")
	public String modify(Model model, PostForm postForm, @PathVariable("id") Integer id) {
		Post post = this.postService.getOnePost(id);
		postForm.setName(post.getName());
		postForm.setSubject(post.getSubject());
		postForm.setContent(post.getContent());
		model.addAttribute("postForm", postForm);
		return "post_form";
	}
	@PostMapping("/modify/{id}")
	public String modify(@Valid PostForm postForm, BindingResult bindingResult, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "post_form";
		}
		Post post = this.postService.getOnePost(id);
		post.setName(postForm.getName());
		post.setSubject(postForm.getSubject());
		post.setContent(postForm.getContent());
		this.postService.modify(post);
		return "redirect:/post/detail/" + id;
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Post post = this.postService.getOnePost(id);
		this.postService.delete(post);
		return "redirect:/post/list";
	}

}
