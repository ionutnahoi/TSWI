package com.project.tswi.repository;

import com.project.tswi.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    @Query("SELECT b FROM WaitingList b where  b.book.id=:idBook and b.user.id=:idUser")
    WaitingList findByUserId(Long idUser, Long idBook);
}
