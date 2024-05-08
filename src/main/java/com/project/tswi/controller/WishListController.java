package com.project.tswi.controller;

import com.project.tswi.entity.Book;
import com.project.tswi.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wishlist")
@CrossOrigin
@AllArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @PostMapping(value = "addwishlist")
    public void addWishList(@RequestParam(value = "idUser") Long idUser, @RequestParam("title") String title) {
        wishListService.addWishList(idUser, title);
    }

    @GetMapping(value = "available")
    public List<Book> available() {
        return wishListService.getAllAvailable();
    }

    @GetMapping(value = "mywishlist")
    public List<Book> myWishList(@RequestParam(value = "idUser") Long idUser) {
        List<Book> bookList = wishListService.myWishList(idUser);
        for (Book book : bookList) {
            System.out.println(book);
        }
        return wishListService.myWishList(idUser);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam("id") Long id, @RequestParam("title") String title) {
        wishListService.delete(id, title);
    }
}
