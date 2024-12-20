package com.web.blog.diary;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
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
public class DiaryForm {
	@Transient
	private Long id;
	
	@NotEmpty(message="제목을 입력해주세요:)")
	@Size(max=100)
	private String subject;
	
	@NotEmpty(message="본문을 입력해주세요:)")
	private String content;
	
	public DiaryForm(String subject, String content) {
		super();
		this.subject = subject;
		this.content = content;
	}
	public Diary toEntity() {
		return new Diary(subject, content, LocalDateTime.now());
	}
	public Diary toEntityModify() {
		return new Diary(id, subject, content, LocalDateTime.now());
	}
	
	
	

}
