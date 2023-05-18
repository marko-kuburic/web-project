package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.dto.ReviewDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double rating;

    @Column
    private String text;

    @Column
    private LocalDate date;

    @ManyToOne
    private User user;

    @OneToOne
    private ShelfItem item;

    private String bookTitle;



    public Review() {
    }

    public Review(double rating, String text, LocalDate date) {
        this.rating = rating;
        this.text = text;
        this.date = date;
    }

    public Review(double rating, String text, LocalDate date, User user, ShelfItem item) {
        this.rating = rating;
        this.text = text;
        this.date = date;
        this.user = user;
        this.item = item;
        this.bookTitle = item.getTitle();
    }

    public Review(ReviewDto reviewDto) {

        this.rating = reviewDto.getRating();
        this.text = reviewDto.getText();
        this.date = reviewDto.getDate();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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
        this.bookTitle = item.getTitle();
    }
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating='" + rating + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
