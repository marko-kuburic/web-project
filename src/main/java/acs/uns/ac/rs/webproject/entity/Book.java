package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.dto.BookDto;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import acs.uns.ac.rs.webproject.entity.Genre;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column (name = "image_path")
    private String imgPath;

    @Column(unique = true)
    private String isbn;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    @Column (name = "number_of_pages")
    private int numberOfPages;

    @Column
    private String description;

    @ManyToOne
    private Genre genre;

    @Column
    private float rating ;

    @OneToOne(mappedBy = "book")
    private ShelfItem item;

    private String genreName;


    public Book() {
    }

    public Book(String title, String imgPath, String isbn, LocalDate releaseDate, int numberOfPages, String description, Genre genre, float rating, ShelfItem item) {
        this.title = title;
        this.imgPath = imgPath;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.item = item;
        this.genreName = genre.getGenre();
    }

    public Book(String title, String imgPath, String isbn, LocalDate releaseDate, int numberOfPages, String description, Genre genre, float rating) {
        this.title = title;
        this.imgPath = imgPath;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.genreName = genre.getGenre();
    }

    public Book(BookDto bookDto) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() { return imgPath; }

    public void setImgPath(String img) { this.imgPath = img; }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        this.genreName = genre.getGenre();
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public ShelfItem getItem() {
        return item;
    }

    public void setItem(ShelfItem item) {
        this.item = item;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", releaseDate=" + releaseDate +
                ", numberOfPages=" + numberOfPages +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", rating=" + rating +
                '}';
    }


}
