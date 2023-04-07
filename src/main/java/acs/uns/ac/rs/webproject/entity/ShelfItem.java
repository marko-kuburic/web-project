package acs.uns.ac.rs.webproject.entity;

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

    @ManyToMany(mappedBy = "items")
    private Set<Shelf> shelves = new HashSet<Shelf>();

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToOne
    private Book book;

    private String title;

    public ShelfItem() {
    }

    public ShelfItem(Set<Shelf> shelves, Set<Review> reviews, Book book) {
        this.shelves = shelves;
        this.reviews = reviews;
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

    public Set<Shelf> getShelves() { return shelves; }

    public void setShelves(Set<Shelf> shelves) { this.shelves = shelves; }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
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
