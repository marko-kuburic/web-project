package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
