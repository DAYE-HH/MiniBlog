package com.web.blog.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
