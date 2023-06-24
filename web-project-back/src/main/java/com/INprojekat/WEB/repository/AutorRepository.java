package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor getByMail(String mail);
    Autor findAutorById(Long id);
    Boolean existsByMail(String mail);
    Boolean existsByLozinka(String lozinka);
    Boolean existsByKorisnickoIme(String korisnickoIme);
}
