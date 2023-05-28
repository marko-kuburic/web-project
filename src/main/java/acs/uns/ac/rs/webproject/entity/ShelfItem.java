package acs.uns.ac.rs.webproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity (name="shelf_item")
public class ShelfItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @OneToOne
    private Review review;

    @OneToOne
    private Book book;

    private String title;

    public ShelfItem() {
    }

    public ShelfItem(Review review, Book book) {
        this.review = review;
        this.book = book;
    }

    public ShelfItem(Shelf shelf, Review review, Book book) {
        this.shelf = shelf;
        this.review = review;
        this.book = book;
        title = book.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.title = book.getTitle();
    }

    @Override
    public String toString() {
        return "ShelfItem{" +
                ", book=" + book +
                '}';
    }
}
