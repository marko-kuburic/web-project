package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZanrRepository extends JpaRepository<Zanr, Long> {
    Zanr findZanrById(Long id);
}
