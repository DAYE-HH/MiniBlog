package com.web.blog.post;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;

@Service
public class PostReviewService {
	@Autowired
	private PostReviewRepository postReviewRepository;

	public void create(Post post, String content) {
		// TODO #1-3-5 : 리뷰 엔티티 생성 및 데이터 세팅
		PostReview postReview = new PostReview();
		postReview.setContent(content);
		postReview.setCreateDate(LocalDateTime.now());	// 서버측의 시간
		postReview.setPost(post);
		// TODO #1-3-6 : 리뷰 엔티티 저장 -> insert SQL 작동
		this.postReviewRepository.save(postReview);
	}
	public PostReview selectOnePostReview(Integer id) {
		Optional<PostReview> oPostReview = this.postReviewRepository.findById(id);
		if(oPostReview.isPresent()) {
			return oPostReview.get();
		}
		throw new DataNotFoundException("review not found");
	}
	public void delete(PostReview postReview) {
		this.postReviewRepository.delete(postReview);
	}
	public void modify(PostReview postReview) {
		this.postReviewRepository.save(postReview);
	}
}
