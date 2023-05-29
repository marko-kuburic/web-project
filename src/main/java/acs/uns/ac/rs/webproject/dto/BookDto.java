package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.service.AuthorService;
import acs.uns.ac.rs.webproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class BookDto {

    Long id;

    String title;

    String ISBN;

    LocalDate releaseDate;

    Integer numberOfPages;

    String about;

    String imagePath;

    long genreId;

    Float rating;

    long authorId;



    public BookDto(Long id, String title, String ISBN, LocalDate releaseDate, Integer numberOfPages, String about, String imagePath, Long genreId, Float rating, Long authorId, long shelfItemId) {
        this.id = id;

        this.title = title;
        this.ISBN = ISBN;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.about = about;
        this.imagePath = imagePath;
        this.genreId = genreId;
        this.rating = rating;
        this.authorId = authorId;
        this.shelfItemId = shelfItemId;
    }



    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BookDto(long id, String name, String ISBN, LocalDate releaseDate, int numberOfPages, String about, Long genreId, float rating, long shelfItemId) {
        this.id = id;

        this.title = name;
        this.ISBN = ISBN;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.about = about;
        this.genreId = genreId;
        this.rating = rating;
        this.shelfItemId = shelfItemId;
    }
    public BookDto(Book book)
    {
        this.id = book.getId();
        this.title = book.getTitle();
        this.ISBN = book.getIsbn();
        this.releaseDate = book.getReleaseDate();
        this.numberOfPages = book.getNumberOfPages();
        this.about = book.getDescription();
        if(book.getGenre()!=null){
        if(book.getGenre().getId()==null)
            this.genreId = -1;
        else
            this.genreId = book.getGenre().getId();
        }
        else
            this.genreId = -1;
        this.rating = book.getRating();
        this.authorId = -1;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
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

    public Long getGenreId() {
        return genreId;
    }

    public void setGenre(Long genre) {
        this.genreId = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", releaseDate=" + releaseDate +
                ", numberOfPages=" + numberOfPages +
                ", about='" + about + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", genreId=" + genreId +
                ", rating=" + rating +
                ", authorId=" + authorId +
                ", shelfItemId=" + shelfItemId +
                '}';
    }
}
