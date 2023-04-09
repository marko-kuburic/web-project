package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Author;
import acs.uns.ac.rs.webproject.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

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

}
