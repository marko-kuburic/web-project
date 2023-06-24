package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getByMail(String mail);
    Korisnik getById(Long id);
    Boolean existsByMail(String mail);
    Boolean existsByLozinka(String lozinka);
    Boolean existsByKorisnickoIme(String korisnickoIme);
}
