package com.project.tswi.controller;

import com.project.tswi.service.BookOwnerService;
import com.project.tswi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("owner")
@CrossOrigin
public class BookOwnerController {
    @Autowired
    private final BookOwnerService bookOwnerService;
    private final BookService bookService;

    public BookOwnerController(BookOwnerService bookOwnerService, BookService bookService) {
        this.bookOwnerService = bookOwnerService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/addOwner")
    public void addBookAndOwner(@RequestParam("idUser") long idUser, @RequestParam("title") String title) {
        bookOwnerService.addBookAndOwner(idUser, bookService.getBookByTitle(title).getId());
    }

    @GetMapping(value = "/bookByUserId")
    public void getBookByUserId(@RequestParam("idUser") long idUser) {
        bookOwnerService.getBookByUserId(idUser);
    }
}