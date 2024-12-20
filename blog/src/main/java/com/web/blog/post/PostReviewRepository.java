package com.web.blog.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.post.PostReview;

public interface PostReviewRepository extends JpaRepository<PostReview, Integer> {

}	
