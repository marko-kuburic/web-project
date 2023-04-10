package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.service.ReviewService;

import java.util.List;

@RestController
public class  ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/api/reviews")
    public List<Review> getReviews(){
        List<Review> reviewList = reviewService.findAll();
        return reviewList;
    }

    @GetMapping("/api/reviews/{id}")
    public Review getReview(@PathVariable(name = "id") Long id){
        Review review = reviewService.findOne(id);
        return review;
    }

    @GetMapping("/api/reviews/search/{bookTitle}")
    public List<Review> getAllByBookTitle(@PathVariable("bookTitle") String bookTitle){
        List<Review> reviewList = reviewService.findAllByBookTitle(bookTitle);
        return reviewList;
    }
    @GetMapping("/api/reviews/search/{user}")
    public List<Review> getAllByUser(@PathVariable("user") String user){
        List<Review> reviewList = reviewService.findAllByUser(user);
        return reviewList;
    }

    @PostMapping("/api/save-review")
    public String saveReview(@RequestBody Review review) {
        this.reviewService.save(review);
        return "Successfully saved an review!";
    }

}
