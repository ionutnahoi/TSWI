package com.project.tswi.service;

import com.project.tswi.entity.Book;
import com.project.tswi.repository.BookRepository;
import com.project.tswi.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;

    public BookService(BookRepository bookRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getbyid(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        if (bookRepository.findById(book.getId()).isPresent())
            bookRepository.save(book);
    }

    public Book getBookByTitle(String bookTitle) {
        return bookRepository.getBookByTitle(bookTitle);
    }

    public List<Book> getAvailableBooks(long id) {
        List<Book> bookList = borrowRepository.getBooksByOwner(id);
        List<Book> allBooks = bookRepository.getAvailableBooks();
        List<Book> newList = new ArrayList<>();
        for (Book allBook : allBooks) {
            boolean ok = true;
            for (Book book : bookList) {
                if (!ok) break;
                if (Objects.equals(allBook.getId(), book.getId()))
                    ok = false;
            }
            if (ok)
                newList.add(allBook);
        }
        return newList;

    }

    public List<Book> getUnavailableBooks(long id) {
        List<Book> bookList = borrowRepository.getBooksByOwner(id);
        List<Book> allBooks = bookRepository.getUnavailableBooks();
        List<Book> newList = new ArrayList<>();

        for (Book allBook : allBooks) {
            boolean ok = true;
            for (Book book : bookList) {
                if (!ok) break;
                if (Objects.equals(allBook.getId(), book.getId()))
                    ok = false;
            }
            if (ok)
                newList.add(allBook);
        }
        return newList;
    }


    public List<Book> getMyBooks(long id) {
    return bookRepository.getMyBooks(id);
    }

    public List<Book> getWaitingList(long idUser) {
        return bookRepository.getWaitingList(idUser);
    }
}
