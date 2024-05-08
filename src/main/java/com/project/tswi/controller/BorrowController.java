package com.project.tswi.controller;

import com.project.tswi.entity.Borrow;
import com.project.tswi.service.BookService;
import com.project.tswi.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("borrow")
@CrossOrigin
public class BorrowController {
    @Autowired
    private final BorrowService borrowService;

    @Autowired
    private final BookService bookService;

    public BorrowController(BorrowService borrowService, BookService bookService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Borrow> getAll() {
        return borrowService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getbyid(@RequestParam Long id) {
        return borrowService.getbyid(id).isPresent() ? borrowService.getbyid(id).get() : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public void addBorrow(@RequestParam("idUser") Long idUser, @RequestParam("title") String title, @RequestParam("period") int days) {
        borrowService.add(idUser, bookService.getBookByTitle(title).getId(), days);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        borrowService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestParam Borrow borrow) {
        borrowService.update(borrow);
    }

    @GetMapping(value = "/seeWhoBorrowed")
    public List<Borrow> getUserWhoBorrowed(@RequestParam("id") Long id) {
        List<Borrow> borrow = borrowService.borrowDetails(id);
        return borrow;
    }

    @GetMapping(value = "/whatIBorrowed")
    public List<Borrow> getWhatIBorrowed(@RequestParam("idUser") Long id) {
        List<Borrow> borrow = borrowService.whatIBorrowed(id);
        return borrow;

    }

    @PutMapping(value = "/extendPeriod")
    public void extendPeriod(@RequestParam("days") int days, @RequestParam("idUser") Long id, @RequestParam("bookName") String bookName) {
        borrowService.updatePeriod(days, id, bookName);
    }

    @GetMapping(value = "checkAvailable")
    public String checkBookAvailable(@RequestParam("title") String title) {
        Borrow borrow = borrowService.checkBookAvailability(title);
        if (borrow == null) {
            if (bookService.getBookByTitle(title) == null)
                return "Carte nu exista";
            return "Cartea este disponibila";
        } else if (borrow.getDate_when_return().isAfter(LocalDate.now())) {
            return "Cartea nu este disponibila si va fi returnata in data de: " + borrow.getDate_when_return();
        }
        return "ERROR";
    }
}
