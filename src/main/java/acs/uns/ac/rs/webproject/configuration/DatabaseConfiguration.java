package acs.uns.ac.rs.webproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    UserRepository userRepository;

    @Bean
    public boolean instantiate(){
        User admin1 = new User("Danilo", "Damjanovic", "chodaliman", "danilodamjanovic@gmail.com", "kadacekrajovojagoniji", LocalDate.of(2002, 12, 11), Role.ADMIN);
        User admin2 = new User("Marko", "Kuburic", "skubi021", "markokuburic@gmail.com", "123456789", LocalDate.of(2002, 8, 21), Role.ADMIN);

        userRepository.save(admin1);
        userRepository.save(admin2);

        return true;
    }
}