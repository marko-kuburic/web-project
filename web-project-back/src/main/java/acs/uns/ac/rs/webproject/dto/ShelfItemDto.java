package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

public class ShelfItemDto {

    private Long id;

    private Review review;

    private BookDto book;

    private String title;

    public ShelfItemDto(ShelfItem sh) {
        this.id = sh.getId();
        this.review = sh.getReview();
        this.book = new BookDto(sh.getBook());
        this.title = sh.getTitle();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = new BookDto(book);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
