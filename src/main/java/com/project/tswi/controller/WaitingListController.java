package com.project.tswi.controller;

import com.project.tswi.service.BookService;
import com.project.tswi.service.UserService;
import com.project.tswi.service.WaitingListService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("waitinglist")
@CrossOrigin
public class WaitingListController {
    private final WaitingListService waitingListService;
    private final BookService bookService;
    private final UserService userService;

    public WaitingListController(WaitingListService waitingListService, BookService bookService, UserService userService) {
        this.waitingListService = waitingListService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("/waiting")
    public void addWaiting(@RequestParam(value = "idUserWhoBorrow") long idUser, @RequestParam(value = "title") String title) {
        waitingListService.addWaitingList(idUser, title);
    }

    @DeleteMapping("/delete")
    public void deleteWaiting(@RequestParam(value = "id") long idUser, @RequestParam(value = "title") String title) {
        waitingListService.delete(idUser,title);
    }
}

