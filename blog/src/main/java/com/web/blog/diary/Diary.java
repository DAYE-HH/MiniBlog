package com.web.blog.diary;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator1")
    @SequenceGenerator(name = "sequence_generator1", sequenceName = "sequence_name1", allocationSize = 1)
	private Long id;
	
	@Column(length = 128)
	private String subject;
	
	@Column(length = 1024)
	private String content;
	
	private LocalDateTime createDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "diary", cascade = CascadeType.REMOVE)
	private List<DiaryReview> diaryReviewList;
	
	public Diary(String subject, String content, LocalDateTime createDate) {
		super();
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}

	public Diary(Long id, String subject, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}
	
	@Column(length = 2048)
	private String document;
	
	private Integer label;

}
