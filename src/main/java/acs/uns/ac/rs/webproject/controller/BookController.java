package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.*;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Book;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShelfItemService shelfItemService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> bookList = bookService.findAll();
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for(Book book : bookList)
        {
            BookDto bookDto = new BookDto (book);
            bookDtos.add(bookDto);
        }
        if(bookList.size() == 0)
            return new ResponseEntity(bookDtos, HttpStatus.NOT_FOUND);
        return new ResponseEntity(bookDtos, HttpStatus.OK);
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable(name = "id") Long id) {

        Book book = bookService.findOne(id);
        if(book == null)
            return ResponseEntity.ofNullable(null);
        BookDto bookDto = new BookDto(book);

        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/api/books/search1/{name}")
    public ResponseEntity<List<Book>> getAllByName(@PathVariable("name") String name) {
        List<Book> bookList = bookService.findAllByName(name);
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for(Book book : bookList)
        {
            BookDto bookDto = new BookDto (book);
            bookDtos.add(bookDto);
        }
        if(bookList.size()==0)
            return new ResponseEntity(bookDtos, HttpStatus.NOT_FOUND);
        return new ResponseEntity(bookDtos, HttpStatus.OK);
    }

    @GetMapping("/api/books/search2/{isbn}")
    public ResponseEntity<List<BookDto>> getAllByIsbn(@PathVariable("isbn") String isbn) {
        List<Book> bookList = this.bookService.findAllByIsbn(isbn);
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for(Book book : bookList)
        {
            BookDto bookDto = new BookDto (book);
            bookDtos.add(bookDto);
        }
        if(bookList.isEmpty())
            return new ResponseEntity(bookDtos, HttpStatus.NOT_FOUND);
        return new ResponseEntity(bookDtos, HttpStatus.OK);
    }

    @GetMapping("/api/books/search3/{genreName}")
    public ResponseEntity<List<BookDto>> getAllByGenreName(@PathVariable("genreName") String genreName) {
        List<Book> bookList = bookService.findAllByGenreName(genreName);
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for(Book book : bookList)
        {
            BookDto bookDto = new BookDto (book);
            bookDtos.add(bookDto);
        }

        if(bookList.isEmpty())
            return new ResponseEntity(bookDtos, HttpStatus.NOT_FOUND);
        return new ResponseEntity(bookDtos, HttpStatus.OK);
    }


    @PostMapping("/api/save-book")
    public String saveBook(@RequestBody Book book) {
        this.bookService.save(book);
        return "Successfully saved a book!";
    }

    @PostMapping("/add-review")
    public ResponseEntity addReview(@ModelAttribute Review2Dto reviewDto)
    {
        if(reviewDto.getDate() == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(reviewDto.getText().isEmpty())
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        Review review = new Review(reviewDto);
       // System.out.print(review.getId());
        reviewService.save(review);
        ShelfItem shelfItem = shelfItemService.findOne(reviewDto.getshelfItemId());
        if(shelfItem==null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        shelfItem.setReview(review);

        shelfItemService.save(shelfItem);
        return new ResponseEntity("Successfully added a review", HttpStatus.OK);
    }

    @PutMapping("/change-review")
    public ResponseEntity putReview(@ModelAttribute Review2Dto reviewDto)
    {
        if(reviewDto.getDate() == null)
            return new ResponseEntity("Date wrong", HttpStatus.NOT_FOUND);

        if(reviewDto.getText().isEmpty())
            return new ResponseEntity("Text empty", HttpStatus.NOT_FOUND);

        Review review = reviewService.findOne(reviewDto.getId());
        if(review == null)
            return new ResponseEntity("No review", HttpStatus.NOT_FOUND);

        review = reviewService.changeReview(review,reviewDto);
        ShelfItem shelfItem = shelfItemService.findOne(reviewDto.getshelfItemId());
        shelfItem.setReview(review);
        shelfItemService.save(shelfItem);
        return new ResponseEntity("Successfully changed a review!", HttpStatus.OK);
    }

    @DeleteMapping("/remove-book/{shelfItemId}/{shelfId}")
    public ResponseEntity removeBook(@PathVariable(name = "shelfItemId") Long shelfItemId, @PathVariable(name="shelfId") Long shelfId ){

        ShelfItem shelfItem = shelfItemService.findOne(shelfItemId);
        Shelf shelf = shelfService.findOne(shelfId);
        if(shelf==null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(shelfItem==null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        Book book = shelfItem.getBook();
        if(shelf.getPrimary())
        {

            if(!shelfService.removeBookFromEverywhere(book))
                return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
            if(shelf.getName().equals("Read"))
            {
                shelfItem.setReview(null);
            }


            return new ResponseEntity("Successfully removed a book", HttpStatus.OK);

        }
        if(shelfService.removeBook(book,shelf))
        return new ResponseEntity("Successfully removed a book", HttpStatus.OK);
        return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/put-shelfItem/{shelfItemID}/{shelfID}")
    public ResponseEntity putBook(@PathVariable(name = "shelfItemId") Long shelfItemId, @PathVariable(name="shelfId") Long shelfId){

        if(shelfItemService.findOne(shelfItemId)==null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(!shelfService.exists(shelfId))
        {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        ShelfItem shelfItem = shelfItemService.findOne(shelfItemId);
        Shelf shelf = shelfService.findOne(shelfId);

        if(!shelf.getPrimary())
        {
            if(!shelfService.isOnPrimary(shelfItem.getBook()))
            {
                return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
            }
            else
            {
                shelfService.addShelfItem(shelfItem, shelf);
            }
        }
        else {

            if(shelf.getName().equals("Read"))
                return new ResponseEntity("redirect:/add-review", HttpStatus.OK);

        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity addBook(BookDto bookDto)
    {
        if(authorService.findOne(bookDto.getAuthorId())==null)
            return new ResponseEntity("No author with that id", HttpStatus.NOT_FOUND);
            
        if(authorService.findOne(bookDto.getAuthorId()).isActive()!=true)
            return new ResponseEntity("Is not acitve", HttpStatus.FORBIDDEN);

        Book book = new Book(bookDto);
        if(bookDto.getGenreId()!=null && genreService.findOne(bookDto.getGenreId())!=null)
        book.setGenre(genreService.findOne(bookDto.getGenreId()));

        bookService.save(book);
        return new ResponseEntity("Success", HttpStatus.OK);
    }
    @PutMapping("/change-book")
    public ResponseEntity updateBook(BookDto bookDto)
    {
        if(authorService.findOne(bookDto.getAuthorId())==null)
            return new ResponseEntity("No author with that id", HttpStatus.NOT_FOUND);
       /* if(authorService.findOne(bookDto.getAuthorId()).isActive()!=true)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);*/

        if(bookService.findOne(bookDto.getId())==null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        Book book = bookService.findOne(bookDto.getId());
        bookService.updateBook(book,bookDto);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

}