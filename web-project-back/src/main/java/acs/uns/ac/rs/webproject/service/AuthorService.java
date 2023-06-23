package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Author;
import acs.uns.ac.rs.webproject.repository.AuthorRepository;
import acs.uns.ac.rs.webproject.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Author findOne(Long id)
    {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if (foundAuthor.isPresent())
            return foundAuthor.get();

        return null;
    }



    public List<Author> findAllByName(String name){return authorRepository.findAllByName(name);}

    public List<Author> findAllBySurname(String surname){return authorRepository.findAllBySurname(surname);}
    public List<Author> findAllByIsActive(Boolean active){return authorRepository.findAllByIsActive(active);}

    public List<Author> findAll(){ return authorRepository.findAll();}

    public Author save(Author author){ return authorRepository.save(author);}

    public boolean addBook(Book book, User user){
        Author temp = this.findOne(user.getId());

        temp.addBook(book);

        return bookRepository.save(book) != null && authorRepository.save(temp) != null;

    }

}
