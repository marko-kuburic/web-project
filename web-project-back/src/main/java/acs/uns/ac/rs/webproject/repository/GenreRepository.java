package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository <Genre, Long> {

    List<Genre> findAllByGenre(String genre);

}
