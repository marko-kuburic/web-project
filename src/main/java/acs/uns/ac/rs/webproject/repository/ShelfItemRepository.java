package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.ShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfItemRepository extends JpaRepository<ShelfItem, Long> {
}
