package acs.uns.ac.rs.webproject.entity;

import java.io.Serializable;

public class Genre implements Serializable {
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setNaziv(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return " " + genre;
    }
}
