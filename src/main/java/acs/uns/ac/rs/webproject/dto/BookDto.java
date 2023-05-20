package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Genre;

import java.time.LocalDate;

public class BookDto {

    long id;
    long shelfId;

    String name;

    String ISBN;

    LocalDate releaseDate;

    int numberOfPages;

    String about;

    Genre genre;

    int rating;

    long authorId;

    public BookDto(long id, long shelfId, String name, String ISBN, LocalDate releaseDate, int numberOfPages, String about, Genre genre, int rating, long shelfItemId) {
        this.id = id;
        this.shelfId = shelfId;
        this.name = name;
        this.ISBN = ISBN;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.about = about;
        this.genre = genre;
        this.rating = rating;
        this.shelfItemId = shelfItemId;
    }

    long shelfItemId;
    public BookDto() {
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public BookDto(long id, long shelfItemId) {
        this.id = id;
        this.shelfItemId = shelfItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getShelfItemId() {
        return shelfItemId;
    }

    public void setShelfItemId(long shelfItemId) {
        this.shelfItemId = shelfItemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShelfId() {
        return shelfId;
    }

    public void setShelfId(long shelfId) {
        this.shelfId = shelfId;
    }
}
