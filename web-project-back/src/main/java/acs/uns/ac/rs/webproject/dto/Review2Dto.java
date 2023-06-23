package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Review;

import java.time.LocalDate;

public class Review2Dto {
    private long id;
    private long shelfItemId;
    private double rating;

    private String text;
    private LocalDate date;

    public Review2Dto() {
    }

    public Review2Dto(double rating, String text, LocalDate date) {
        this.rating = rating;
        this.text = text;
        this.date = date;
    }

    public Review2Dto(long shelfItemId, double rating, String text, LocalDate date) {
        this.shelfItemId = shelfItemId;
        this.rating = rating;
        this.text = text;
        this.date = date;
    }

   public Review2Dto(Review review)
   {
       this.id = review.getId();
       this.date = review.getDate();
       this.text = review.getText();
       this.rating = review.getRating();
       this.shelfItemId = -1;
   }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getshelfItemId() {
        return shelfItemId;
    }

    public void setshelfItemId(long shelfId) {
        this.shelfItemId = shelfId;
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

}
