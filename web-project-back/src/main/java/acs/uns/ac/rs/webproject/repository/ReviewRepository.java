package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository <Review, Long> {

    List<Review> findAllByBookTitle(String name);

    List<Review> findAllByUser(User user);
}
