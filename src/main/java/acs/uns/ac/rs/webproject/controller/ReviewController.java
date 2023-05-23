package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.BookDto;
import acs.uns.ac.rs.webproject.dto.Review2Dto;
import acs.uns.ac.rs.webproject.dto.ReviewDto;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(){
        List<Review> reviewList = reviewService.findAll();
        if(reviewList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        List<Review2Dto> review2Dtos = new ArrayList<Review2Dto>();
        for(Review review : reviewList)
        {
            Review2Dto reviewDto = new Review2Dto(review);

            review2Dtos.add(reviewDto);
        }
        return new ResponseEntity(review2Dtos, HttpStatus.OK);
    }

    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable(name = "id") Long id){
        Review review = reviewService.findOne(id);
        if(review==null)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        ReviewDto reviewDto = new ReviewDto(review);
        return new ResponseEntity(reviewDto, HttpStatus.OK);
    }

    @GetMapping("/api/reviews/search1/{bookTitle}")
    public ResponseEntity<List<ReviewDto>> getAllByBookTitle(@PathVariable("bookTitle") String bookTitle){
        List<Review> reviewList = reviewService.findAllByBookTitle(bookTitle);
        if(reviewList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();
        for(Review review : reviewList)
        {
            ReviewDto reviewDto = new ReviewDto (review);
            reviewDtos.add(reviewDto);
        }
        return new ResponseEntity(reviewDtos, HttpStatus.OK);
    }
    @GetMapping("/api/reviews/search2/{userId}")
    public ResponseEntity<List<ReviewDto>> getAllByUser(@PathVariable("userId") Long userId){
        List<Review> reviewList = reviewService.findAllByUser(userService.getById(userId));
        if(reviewList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();
        for(Review review : reviewList)
        {
            ReviewDto reviewDto = new ReviewDto (review);
            reviewDtos.add(reviewDto);
        }
        return new ResponseEntity(reviewDtos, HttpStatus.OK);
    }

    @PostMapping("/api/save-review")
    public String saveReview(@RequestBody Review review) {
        this.reviewService.save(review);
        return "Successfully saved an review!";
    }

}
