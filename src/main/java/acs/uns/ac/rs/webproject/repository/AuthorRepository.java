package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    List<Author> findAllByName(String name);

    List<Author> findAllByIsActive(Boolean isActive);

    List<Author> findAllBySurname(String surname);



}
