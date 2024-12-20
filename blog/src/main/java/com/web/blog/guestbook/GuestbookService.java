package com.web.blog.guestbook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.blog.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestbookService {
	private final GuestbookRepository guestbookRepository;

	public List<Guestbook>getAllGuestbook() {
		return this.guestbookRepository.findAll();
	}
			public Guestbook getOneGuestbook(Integer id) {
				Optional<Guestbook> oGuestbook = this.guestbookRepository.findById(id);
				if(oGuestbook.isPresent()) {
					return oGuestbook.get();
				}
				throw new DataNotFoundException("guestbook not found");
			}
			public GuestbookForm getOneGuestbookForm(Integer id) {
				Optional<Guestbook> oGuestbook = this.guestbookRepository.findById(id);
				if(oGuestbook.isPresent()) {
					Guestbook g = oGuestbook.get();
					return new GuestbookForm(g.getName(), g.getContent());
					
				}
				throw new DataNotFoundException("guestbook not found");
			}
			public void create(String name, String content, LocalDateTime createDate) {
				Guestbook g = new Guestbook();
				g.setName(name);
				g.setContent(content);
				g.setCreateDate(LocalDateTime.now());
				this.guestbookRepository.save(g);		
	}
			public void modify(Guestbook guestbook) {
				this.guestbookRepository.save(guestbook);
			}
			public void delete(Guestbook guestbook) {
				this.guestbookRepository.delete(guestbook);
			}
			

}
