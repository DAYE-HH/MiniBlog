package com.web.blog.guestbook;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GuestbookForm {

	@Transient
	private Integer id;
	
	@NotEmpty(message="작성자명")
	private String name;
	
	@NotEmpty(message="본문을 입력해주세요:)")
	private String content;
	
	public GuestbookForm(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}
}
