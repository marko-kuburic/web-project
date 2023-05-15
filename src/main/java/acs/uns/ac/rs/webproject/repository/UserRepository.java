package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //primer ti dajem
    List<User> findAllByName(String name);

    List<User> findAllBySurname(String surname);

    List<User> findAllByUsername(String username);

    User getByUsername(String username);

    User getByMail(String mail);


}
