package com.web.blog.post;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PostReview {
	@Id
	@GeneratedValue(strategy = GenerationType
	.SEQUENCE, generator = "sequence_generator2")
    @SequenceGenerator(name = "sequence_generator2", sequenceName = "sequence_name2", allocationSize = 1)
	private Integer id;
	
	@Column(length = 512)
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Post post;
}
