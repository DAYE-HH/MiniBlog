package com.web.blog.diary;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiaryReviewForm {
	@NotEmpty(message="내용을 입력해주세요:)")
	@Size(max=100)
	private String content;
}
