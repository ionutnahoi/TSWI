package com.project.tswi.service;

import com.project.tswi.entity.Borrow;
import com.project.tswi.repository.BookOwnerRepository;
import com.project.tswi.repository.BookRepository;
import com.project.tswi.repository.BorrowRepository;
import com.project.tswi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final BookOwnerRepository bookOwnerRepository;

    public BorrowService(BorrowRepository borrowRepository, UserRepository userRepository, BookRepository bookRepository, BookOwnerRepository bookOwnerRepository) {
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookOwnerRepository = bookOwnerRepository;
    }

    public List<Borrow> getAll() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getbyid(Long id) {
        return borrowRepository.findById(id);
    }

    public void deleteById(Long id) {
        borrowRepository.deleteById(id);
    }

    public void update(Borrow borrow) {
        if (borrowRepository.findById(borrow.getId_borrow()).isPresent())
            borrowRepository.save(borrow);
    }

    public void add(Long idUser, Long idBook, int borrowDays) {
        Borrow borrow = new Borrow();
        borrow.setUser_who_borrowed(userRepository.findById(idUser).get());
        borrow.setBorrowed_book(bookRepository.findById(idBook).get());
        borrow.setId_owner_book(bookOwnerRepository.getBookOwnerByID(idBook));
        borrow.setDate_when_borrowed(LocalDate.now());
        borrow.setDate_when_return(LocalDate.now().plusDays(borrowDays));
        borrowRepository.save(borrow);
    }

    public List<Borrow> borrowDetails(Long id) {
        return  borrowRepository.getBorrowDetails(id);
    }

    public Borrow updatePeriod(long days, Long idUser, String bookName) {
        Borrow borrow = borrowRepository.getBorrowByIdUserAndBookName(idUser, bookRepository.getIdByTitle(bookName));
        borrow.setDate_when_return(borrow.getDate_when_return().plusDays(days));
        borrowRepository.save(borrow);
        return borrow;
    }
    public Borrow checkBookAvailability(String title){
        return borrowRepository.checkBookAvailability(title);
    }

    public List<Borrow> whatIBorrowed(Long id) {
        return borrowRepository.getWhatIBorrowed(id);
    }
}
