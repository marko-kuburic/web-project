package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.uns.ac.rs.webproject.entity.Author;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.repository.AuthorRepository;
import acs.uns.ac.rs.webproject.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;


    public Book findOne(Long id)
    {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent())
            return foundBook.get();

        return null;
    }

    public boolean exists(Long id)
    {
        Optional<Book> foundBook = bookRepository.findById(id);
        if(foundBook.isPresent())
            return true;
        return false;
    }

    public List<Book> findAllByName(String title)
    {
        List<Book> list = bookRepository.findAll();

        for(Book b : list)
            if(!b.getTitle().contains(title))
                list.remove(b);

        return list;
    }

    public List<Book> findAllByIsbn(String isbn)
    {
        List<Book> list = bookRepository.findAll();

        for(Book b : list)
            if(!b.getIsbn().contains(isbn))
                list.remove(b);

        return list;
    
    }

    public List<Book> findAllByAuthor(String author)
    {
        List<Author> authors = authorService.findAll();

        List<Book> list = new ArrayList();

        for(Author a : authors)
            if(a.getName().contains(author))
                list.addAll(a.getBookSet());

        return list;
    }

    public List<Book> findAllByGenreName(String genre){return bookRepository.findAllByTitle(genre);}

    public List<Book> findAll(){ return bookRepository.findAll();}

    public Book save(Book book){ return bookRepository.save(book);}

    public void updateBook(Book book, BookDto bookDto)
    {
        System.out.print(bookDto);
        if(bookDto.getISBN() != null)
            book.setIsbn(bookDto.getISBN());
        if(bookDto.getName() != null)
            book.setTitle(bookDto.getName());
        if(bookDto.getRating() > 0 && bookDto.getRating() <6)
            book.setRating(bookDto.getRating());
        if(bookDto.getGenreId() != null){
            if(genreService.findOne(bookDto.getGenreId())!=null)
            book.setGenre(genreService.findOne(bookDto.getGenreId()));}
        if(bookDto.getNumberOfPages() != 0)
            book.setNumberOfPages(bookDto.getNumberOfPages());
        if(bookDto.getAbout() != null)
            book.setDescription(bookDto.getAbout());
        if(bookDto.getISBN() != null)
            book.setIsbn(bookDto.getISBN());

        save(book);

    }
}
