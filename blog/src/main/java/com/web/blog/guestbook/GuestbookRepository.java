package com.web.blog.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Integer> {

}
