package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findOne(Long id)
    {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent())
            return foundBook.get();

        return null;
    }

    public List<Book> findAllByName(String title){return bookRepository.findAllByTitle(title);}
    public List<Book> findAllByIsbn(String isbn){return bookRepository.findAllByIsbn(isbn);}
    public List<Book> findAllByGenreName(String genre){return bookRepository.findAllByTitle(genre);}

    public List<Book> findAll(){ return bookRepository.findAll();}

    public Book save(Book book){ return bookRepository.save(book);}

}
