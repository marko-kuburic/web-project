package acs.uns.ac.rs.webproject.entity;

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

    @Column
    private String isbn;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    @Column (name = "number_of_pages")
    private int numberOfPages;

    @Column
    private String description;

    @Column
    private Genre genre;

    @Column
    private float rating ;

    @OneToOne(mappedBy = "book")
    private ShelfItem item;

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
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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
