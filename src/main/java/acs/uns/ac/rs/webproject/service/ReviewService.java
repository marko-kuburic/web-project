package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review findOne(Long id)
    {
        Optional<Review> foundReview = reviewRepository.findById(id);
        if (foundReview.isPresent())
            return foundReview.get();

        return null;
    }

    public Review changeReview(Review review, ReviewDto reviewDto)
    {
        review.setDate(reviewDto.getDate());
        review.setText(review.getText());
        review.setRating(reviewDto.getRating());
        return review;
    }
    public List<Review> findAllByBookTitle(String title){return reviewRepository.findAllByBookTitle(title);}
    public List<Review> findAllByUser(String user){return reviewRepository.findAllByUser(user);}

    public List<Review> findAll(){ return reviewRepository.findAll();}

    public Review save(Review review){ return reviewRepository.save(review);}

}
