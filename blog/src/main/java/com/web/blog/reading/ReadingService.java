package com.web.blog.reading;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;
import com.web.blog.post.PostRepository;
import com.web.blog.reading.Reading;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReadingService {
	private final ReadingRepository readingRepository;

	public List<Reading> getAllReading() {
		return this.readingRepository.findAll();
	}
	
	public Reading getOneReading(Integer id) {
		Optional<Reading> oReading = this.readingRepository.findById(id);
		if(oReading.isPresent()) {
			return oReading.get();
		}
		// 커스텀 예외 상황
		throw new DataNotFoundException("post not found");
	}
	public ReadingForm getOneReadingForm(Integer id) {
		Optional<Reading> oReading = this.readingRepository.findById(id);
		if(oReading.isPresent()) {
			Reading r = oReading.get();
			return new ReadingForm(r.getSubject(), r.getContent());
			
		}
		// 커스텀 예외 상황
		throw new DataNotFoundException("post not found");
	}
	public void create(String subject, String content, LocalDateTime createDate) {
		Reading r = new Reading();
		r.setSubject(subject);
		r.setContent(content);
		r.setCreateDate(LocalDateTime.now());
		this.readingRepository.save(r);
	}
	
	public void modify(Reading reading) {
		this.readingRepository.save(reading);
	}
	
	public void delete(Reading reading) {
		this.readingRepository.delete(reading);
	}

}
