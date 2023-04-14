package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.service.BookService;

import java.util.List;

@RestController
public class  BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        List<Book> bookList = bookService.findAll();
        return bookList;
    }

    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable(name = "id") Long id) {
        Book book = bookService.findOne(id);
        return book;
    }

    @GetMapping("/api/books/search/{name}")
    public List<Book> getAllByName(@PathVariable("name") String name) {
        List<Book> bookList = bookService.findAllByName(name);
        return bookList;
    }

    @GetMapping("/api/books/search/{isbn}")
    public List<Book> getAllByIsbn(@PathVariable("isbn") String isbn) {
        List<Book> bookList = bookService.findAllByIsbn(isbn);
        return bookList;
    }

    @GetMapping("/api/books/search/{genreName}")
    public List<Book> getAllByGenreName(@PathVariable("genreName") String genreName) {
        List<Book> bookList = bookService.findAllByIsbn(genreName);
        return bookList;
    }


    @PostMapping("/api/save-book")
    public String saveBook(@RequestBody Book book) {
        this.bookService.save(book);
        return "Successfully saved a book!";
    }

}