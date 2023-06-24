package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KorisnikRecenzijaDto {
    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String profilnaSlika;

    public KorisnikRecenzijaDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.profilnaSlika = korisnik.getProfilnaSlika();
    }

    public KorisnikRecenzijaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }
}
