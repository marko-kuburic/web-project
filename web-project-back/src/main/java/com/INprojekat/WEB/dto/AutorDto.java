package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AutorDto {
    private boolean aktivnost;
    private Set<Knjiga> knjige = new HashSet<>();

    private String ime;

    private String prezime;
    private String korisnickoIme;

    private String mail;

    private String lozinka;

    private Date datumRodjenja;

    private String profilnaSlika;

    private String opis;

    private Korisnik.Uloga uloga;

    public AutorDto(boolean aktivnost, String ime, String prezime, String profilnaSlika) {
        this.aktivnost = aktivnost;
        this.ime = ime;
        this.prezime = prezime;
        this.profilnaSlika = profilnaSlika;
    }

    public AutorDto(boolean aktivnost) {
        this.aktivnost = aktivnost;
    }

    public AutorDto(Autor autor) {
        this.aktivnost = autor.getAktivnost();
        this.ime = autor.getIme();
        this.prezime = autor.getPrezime();
        this.profilnaSlika = autor.getProfilnaSlika();
        this.korisnickoIme = autor.getKorisnickoIme();
        this.datumRodjenja = autor.getDatumRodjenja();
        this.opis = autor.getOpis();
        this.mail = autor.getMail();
    }
    public AutorDto() {
    }

    public boolean isAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(boolean aktivnost) {
        this.aktivnost = aktivnost;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(Set<Knjiga> knjige) {
        this.knjige = knjige;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Korisnik.Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Korisnik.Uloga uloga) {
        this.uloga = uloga;
    }
}
