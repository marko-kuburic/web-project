package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre findOne(Long id)
    {
        Optional<Genre> foundGenre = genreRepository.findById(id);
        if (foundGenre.isPresent())
            return foundGenre.get();

        return null;
    }

    public List<Genre> findAllByGenre(String genre){return genreRepository.findAllByGenre(genre);}

    public List<Genre> findAll(){ return genreRepository.findAll();}

    public Genre save(Genre genre){ return genreRepository.save(genre);}

}
