package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {

    List<Book> findAllByTitle(String title);

    List<Book> findAllByIsbn(String isbn);

    Book findByName(String name);
    List<Book> findAllByGenreName(String genre);

}
