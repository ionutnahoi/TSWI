package com.project.tswi.service;

import com.project.tswi.entity.BookOwner;
import com.project.tswi.repository.BookOwnerRepository;
import com.project.tswi.repository.BookRepository;
import com.project.tswi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOwnerService {
    @Autowired
    private final BookOwnerRepository bookOwnerRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    public BookOwnerService(BookOwnerRepository bookOwnerRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.bookOwnerRepository = bookOwnerRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookOwner> getAll() {
        return bookOwnerRepository.findAll();
    }

    public Optional<BookOwner> getbyid(Long id) {
        return bookOwnerRepository.findById(id);
    }

    public void addBookAndOwner(Long idUser, long idBook) {

        BookOwner bookOwner = new BookOwner();
        bookOwner.setUser(userRepository.findById(idUser).get());
        bookOwner.setBook(bookRepository.findById(idBook)
                .get());
        bookOwnerRepository.save(bookOwner);
    }

    public void deleteById(Long id) {
        bookOwnerRepository.deleteById(id);
    }

    public void update(BookOwner bookOwner) {
        if (bookOwnerRepository.findById(bookOwner.getId()).isPresent())
            bookOwnerRepository.save(bookOwner);
    }

    public void getBookByUserId(Long id) {
        bookOwnerRepository.getBookOwnerByID(id);
    }
}

