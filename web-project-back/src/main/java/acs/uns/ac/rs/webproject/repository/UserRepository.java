package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //primer ti dajem
    List<User> findByName(String name);

    List<User> findBySurname(String surname);

    List<User> findAllByUsername(String username);

    User getByName(String name);

    User getBySurname(String surname);

    User getByUsername(String username);

    User getByMail(String mail);

    User findById(long id);



}
