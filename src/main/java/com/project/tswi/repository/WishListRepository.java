package com.project.tswi.repository;

import com.project.tswi.entity.Book;
import com.project.tswi.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Query("SELECT b.book FROM WishList b where b.user.id=:idUser ")
    List<Book> getWishListByUserID(Long idUser);

    @Query("SELECT b from WishList b where b.user.id=:idUser and b.book.title=:title")
    WishList getWishListByUserAndBook(Long idUser, String title);
}
