package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Polica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicaRepository extends JpaRepository<Polica, Long> {
    Boolean existsByNaziv(String naziv);

    void deleteById(Long id);
}
