package com.web.blog.diary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;
import com.web.blog.diary.Diary;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class DiaryService {
	@Autowired
	private DiaryRepository diaryRepository;
	private final int PAGE_SIZE = 10;
	
	public List<Diary>getAllDiary() {
		return this.diaryRepository.findAll();
	}

	// id를 넣어서 일치하는 Post 엔티티 객체 리턴
		public Diary getOneDiary(Long id) {
			Optional<Diary> oDiary = this.diaryRepository.findById(id);
			if(oDiary.isPresent()) {
				return oDiary.get();
			}
			// 커스텀 예외 상황
			throw new DataNotFoundException("diary not found");
		}
		public DiaryForm getOneDiaryForm(Long id) {
			Optional<Diary> oDiary = this.diaryRepository.findById(id);
			if(oDiary.isPresent()) {
				Diary d = oDiary.get();
				return new DiaryForm(d.getSubject(), d.getContent());
				
			}
			// 커스텀 예외 상황
			throw new DataNotFoundException("diary not found");
		}
		public void create(String subject, String content, LocalDateTime createDate) {
			Diary d = new Diary();
			d.setSubject(subject);
			d.setContent(content);
			d.setCreateDate(LocalDateTime.now());
			this.diaryRepository.save(d);		
}
		public void modify(Diary diary) {
			this.diaryRepository.save(diary);
		}
		public void delete(Diary diary) {
			this.diaryRepository.delete(diary);
		}
		
		public Page<Diary> getList(int page) {	
		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		return diaryRepository.findAll( pageable );
	}
		public Page<Diary> getList2(int page, String keyword) {
			List<Sort.Order> sorts = new ArrayList<>();	
			sorts.add(Sort.Order.desc("id"));
			Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by( sorts ));
			Specification<Diary> sf = complexSearch( keyword );
			return diaryRepository.findAll( sf, pageable );
		}
		private Specification<Diary> complexSearch( String keyword ) {
			return new Specification<>() {
				@Override
				public Predicate toPredicate(Root<Diary> root, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					query.distinct(true);
//					Join<Diary, ChatbotSheet> r2 = root.join("lable", JoinType.LEFT);
					return criteriaBuilder.like(root.get("document"), "%"+ keyword +"%")
							;
				}
			};
		}

		}
