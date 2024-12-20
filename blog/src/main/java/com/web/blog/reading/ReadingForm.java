package com.web.blog.reading;

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
public class ReadingForm {
	@Transient
	private Integer id;
	
	@NotEmpty(message="제목을 입력해주세요:)")
	@Size(max=100)
	private String subject;
	
	@NotEmpty(message="본문을 입력해주세요:)")
	private String content;
	
	public ReadingForm(String subject, String content) {
		super();
		this.subject = subject;
		this.content = content;
	}

}
