package com.project.tswi.repository;

import com.project.tswi.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    @Query("select b.user.id from BookOwner b where b.book.id=:id ")
    Long getBookOwnerByID(Long id);
}
