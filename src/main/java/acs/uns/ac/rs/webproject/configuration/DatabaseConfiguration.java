package acs.uns.ac.rs.webproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.GenreRepository;
import acs.uns.ac.rs.webproject.repository.ReviewRepository;
import acs.uns.ac.rs.webproject.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    GenreRepository genreRepository;

    @Bean
    public boolean instantiate(){
        User admin1 = new User("Danilo", "Damjanovic", "chodaliman", "danilodamjanovic@gmail.com", "kadacekrajovojagoniji", LocalDate.of(2002, 12, 11), Role.ADMIN);
        User admin2 = new User("Marko", "Kuburic", "skubi021", "markokuburic@gmail.com", "123456789", LocalDate.of(2002, 8, 21), Role.ADMIN);
        User author1 = new User("Marko", "Kuburic", "marko1", "marko@gmail.com", "1234", LocalDate.of(2002, 8, 21), Role.AUTHOR);
        User reader1 =  new User("Nikola", "Nikolic", "nikola1", "nikola@gmail.com", "1234", LocalDate.of(2003, 8, 21), Role.READER);

        Genre g1 = new Genre("KLASIKA");
        Genre g2 = new Genre("DOMACI");

        //ShelfItem item1 = new ShelfItem(review1, null)
        
        //Review review1  = new Review(5.0, "NAJBOLJA KNJIGA IKADA", LocalDate.of(2023, 8, 21));

        userRepository.save(admin1);
        userRepository.save(admin2);
        userRepository.save(author1);
        userRepository.save(reader1);
        genreRepository.save(g1);
        genreRepository.save(g2);
        //reviewRepository.save(review1);



        return true;
    }
}