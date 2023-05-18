package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.BookDto;
import acs.uns.ac.rs.webproject.dto.ReviewDto;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.ReviewService;
import acs.uns.ac.rs.webproject.service.ShelfItemService;
import acs.uns.ac.rs.webproject.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.service.BookService;

import java.util.List;

@RestController
public class  BookController {
    @Autowired
    private BookService bookService;

    private ShelfService shelfService;

    private ReviewService reviewService;

    private ShelfItemService shelfItemService;

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

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute ReviewDto reviewDto)
    {
        if(reviewDto.getDate() == null)
            return "fail"; //treba videt sta cemo

        if(reviewDto.getText().isEmpty())
            return "fail"; //treba videt sta cemo

        Review review = new Review(reviewDto);
        Book book = bookService.findOne(reviewDto.getBookId());
        ShelfItem shelfItem = new ShelfItem(review,book);
        shelfItemService.save(shelfItem);
        return "success";
    }

    @PutMapping("/change-review")
    public String putReview(@ModelAttribute ReviewDto reviewDto)
    {
        if(reviewDto.getDate() == null)
            return "fail"; //treba videt sta cemo

        if(reviewDto.getText().isEmpty())
            return "fail"; //treba videt sta cemo

        Review review = reviewService.findOne(reviewDto.getId());
        if(review == null)
            return "fail"; //treba videt

        review = reviewService.changeReview(review,reviewDto);
        ShelfItem shelfItem = shelfItemService.findOne(reviewDto.getItemId());
        shelfItem.setReview(review);
        shelfItemService.save(shelfItem);
        return "success";
    }

    @DeleteMapping("/remove-book")
    public void removeBook(@ModelAttribute BookDto bookDto){

        if(!bookService.exists(bookDto.getId()))
            return ;

        if(shelfItemService.findOne(bookDto.getShelfItemId())==null)
        {
            return ;
        }

        Book book = bookService.findOne(bookDto.getId());
        ShelfItem shelfItem = shelfItemService.findOne(bookDto.getShelfItemId());
        Shelf shelf = shelfService.findOne(bookDto.getShelfId());

        if(shelf.getPrimary())
        {
            shelfService.removeBookFromEverywhere(book);
            if(shelf.getName().equals("Read"))
            {
                shelfItem.setReview(null);
            }
        }
        shelfService.removeBook(book,shelf);

    }

    @PutMapping("/put-book")
    public String putBook(@ModelAttribute BookDto bookDto){

        if(!bookService.exists(bookDto.getId()))
            return "fail"; //treba videt sta cemo

        if(!shelfService.exists(bookDto.getShelfId()))
        {
            return "fail"; //treba videt sta cemo
        }

        Book book = bookService.findOne(bookDto.getId());
        Shelf shelf = shelfService.findOne(bookDto.getShelfId());

        if(!shelf.getPrimary())
        {
            if(!shelfService.isOnPrimary(book))
            {
                return "have to add book to a primary shelf first!";
            }
            else
            {
                shelfService.addBook(book, shelf);
            }
        }
        else {

            if(shelf.getName().equals("Read"))
                return "redirect:/add-review";

        }
        return "success";
    }

}