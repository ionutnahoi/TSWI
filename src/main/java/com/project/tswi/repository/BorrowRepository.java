package com.project.tswi.repository;

import com.project.tswi.entity.Book;
import com.project.tswi.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    @Query("SELECT b from Borrow b where b.user_who_borrowed.id=:idOwner")
    List<Borrow> getBorrowDetails(Long idOwner);

    @Query("SELECT b from BookOwner b where b.book.id=:idBook")
    Long idUserByBookId(Long idBook);

    @Query("select b from Borrow b where b.user_who_borrowed.id=:idUser AND b.borrowed_book.id=:bookID")
    Borrow getBorrowByIdUserAndBookName(Long idUser, Long bookID);

    @Query("SELECT b from Borrow b where b.borrowed_book.title=:title")
    Borrow checkBookAvailability(String title);

    @Query("SELECT b.book from BookOwner b where b.user.id=:id ")
    List<Book> getBooksByOwner(long id);

    @Query("SELECT b from Borrow b where b.id_owner_book=:id ")
    List<Borrow> getWhatIBorrowed(Long id);
}
