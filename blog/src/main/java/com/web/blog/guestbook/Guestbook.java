package com.web.blog.guestbook;

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
public class Guestbook {
	
	@Id
	@GeneratedValue(strategy = GenerationType
	.SEQUENCE, generator = "sequence_generator2")
    @SequenceGenerator(name = "sequence_generator2", sequenceName = "sequence_name2", allocationSize = 1)
	private Integer id;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 128)
	private String content; 
	
	private LocalDateTime createDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "guestbook", cascade = CascadeType.REMOVE)
	private List<GuestbookReview> guestbookReviewList;
	
	public Guestbook(String name, String content, LocalDateTime createDate) {
		super();
		this.name = name;
		this.content = content;
		this.createDate = createDate;
	}
	
	public Guestbook(Integer id, String name, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.createDate = createDate;
	}
	

}
