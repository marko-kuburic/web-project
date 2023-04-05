package acs.uns.ac.rs.webproject.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rating;

    @Column
    private String text;

    @Column
    private LocalDate date;

    @ManyToOne
    private User user;

    @ManyToOne
    private ShelfItem item;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShelfItem getItem() {
        return item;
    }

    public void setItem(ShelfItem item) {
        this.item = item;
    }
}
