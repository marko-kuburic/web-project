package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelfRepository extends JpaRepository <Shelf, Long> {

    List<Shelf> findAllByName(String name);

    Shelf getByName(String name);

    Shelf save(Shelf shelf);

}
