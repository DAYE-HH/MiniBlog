package com.web.blog.post;

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
public class Post {

	@Id
	// 아래 두개는 뭔지 모르겠네
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator1")
    @SequenceGenerator(name = "sequence_generator1", sequenceName = "sequence_name1", allocationSize = 1)
	private Integer id;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 128 )
	private String subject;
	
	@Column(length = 1024)
	private String content;
	
	private LocalDateTime createDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<PostReview> postReviewList;
	
	public Post(String name, String subject, String content, LocalDateTime createDate) {
		super();
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}

	public Post(Integer id, String name, String subject, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}
	
}
