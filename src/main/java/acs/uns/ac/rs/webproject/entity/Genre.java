package acs.uns.ac.rs.webproject.entity;

import java.io.Serializable;

public class Genre implements Serializable {
    private String genre;

    public Genre() {
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
