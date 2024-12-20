package com.web.blog.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;
import com.web.blog.post.Post;
import com.web.blog.post.PostForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	
	public List<Post>getAllPost() {
		return this.postRepository.findAll();
	}
	// id를 넣어서 일치하는 Post 엔티티 객체 리턴
			public Post getOnePost(Integer id) {
				Optional<Post> oPost = this.postRepository.findById(id);
				if(oPost.isPresent()) {
					return oPost.get();
				}
				// 커스텀 예외 상황
				throw new DataNotFoundException("post not found");
			}
			public PostForm getOnePostForm(Integer id) {
				Optional<Post> oPost = this.postRepository.findById(id);
				if(oPost.isPresent()) {
					Post p = oPost.get();
					return new PostForm(p.getName(), p.getSubject(), p.getContent());
					
				}
				// 커스텀 예외 상황
				throw new DataNotFoundException("post not found");
			}
			public void create(String name, String subject, String content, LocalDateTime createDate) {
				Post p = new Post();
				p.setName(name);
				p.setSubject(subject);
				p.setContent(content);
				p.setCreateDate(LocalDateTime.now());
				this.postRepository.save(p);
			}
			
			public void modify(Post post) {
				this.postRepository.save(post);
			}
			
			public void delete(Post post) {
				this.postRepository.delete(post);
			}
			
//			public Post createEx(Post p) {
//				this.postRepository.save(p);
//				return p;
//			}
}
