package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    Boolean existsByNaslov(String naziv);
    Optional<Knjiga> findById(Long id);
}
