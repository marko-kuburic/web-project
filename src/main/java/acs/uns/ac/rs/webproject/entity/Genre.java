package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.dto.GenreDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre;

    @OneToMany (mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Genre() {
    }

    public Genre(GenreDto g) {
        this.genre = g.getGenre();
    }

    public Long getId() {
        return id;
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return " " + genre;
    }
}
