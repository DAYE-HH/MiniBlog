package com.web.blog.post;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostForm {
	
		@Transient
		private Integer id;
		
		@NotEmpty(message="작성자명")
		private String name;
		
		@NotEmpty(message="제목을 입력해주세요:)")
		@Size(max=100)
		private String subject;
		
		@NotEmpty(message="본문을 입력해주세요:)")
		private String content;
		
		public PostForm(String name, String subject, String content) {
			super();
			this.name = name;
			this.subject = subject;
			this.content = content;
		}
}
