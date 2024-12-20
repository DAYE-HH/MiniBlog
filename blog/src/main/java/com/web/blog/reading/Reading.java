package com.web.blog.reading;

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
public class Reading {
	
	@Id
	// 아래 두개는 뭔지 모르겠네
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator1")
    @SequenceGenerator(name = "sequence_generator1", sequenceName = "sequence_name1", allocationSize = 1)
	private Integer id;
	
	@Column(length = 128 )
	private String subject;
	
	@Column(length = 1024)
	private String content;
	
	private LocalDateTime createDate;
	
	public Reading (String subject, String content, LocalDateTime createDate) {
		super();
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}

	public Reading(Integer id, String subject, String content, LocalDateTime createDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
	}

}
