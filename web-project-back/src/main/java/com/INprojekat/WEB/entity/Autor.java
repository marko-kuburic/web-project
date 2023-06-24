package com.INprojekat.WEB.entity;

import com.INprojekat.WEB.dto.AutorRegisterDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor extends Korisnik implements Serializable {

    @Column
    private boolean aktivnost;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Knjiga> knjige = new HashSet<>();

    public Autor(String ime, String prezime, String korisnickoIme, String mail, String lozinka, String profilnaSlika, String opis, Uloga uloga, boolean aktivnost) {
        super(ime, prezime, korisnickoIme, mail, lozinka, profilnaSlika, opis, uloga);
        this.aktivnost = aktivnost;
    }

    public Autor(Korisnik korisnik, boolean aktivnost){
        this.setId(korisnik.getId());
        this.setIme(korisnik.getIme());
        this.setPrezime(korisnik.getPrezime());
        this.setKorisnickoIme(korisnik.getKorisnickoIme());
        this.setMail(korisnik.getMail());
        this.setLozinka(korisnik.getLozinka());
        this.setProfilnaSlika(korisnik.getProfilnaSlika());
        this.setOpis(korisnik.getOpis());
        this.setUloga(korisnik.getUloga());
        this.aktivnost = aktivnost;
    }
    public Autor(AutorRegisterDto dto){
        this.setIme(dto.getIme());
        this.setPrezime(dto.getPrezime());
        this.setKorisnickoIme(dto.getKorisnickoIme());
        this.setMail(dto.getMail());
        this.setLozinka(dto.getLozinka());
        this.setUloga(Uloga.AUTOR);
    }
    public Autor(boolean aktivnost, Set<Knjiga> knjige) {
        this.aktivnost = aktivnost;
        this.knjige = knjige;
    }
    public Autor(boolean aktivnost) {
        this.aktivnost = aktivnost;
    }
    public Autor() {
    }

    public void addKnjiga(Knjiga knjiga) {
        knjige.add(knjiga);
        knjiga.setAutor(this);
    }

    public boolean getAktivnost() { return aktivnost; }

    public void setAktivnost(boolean aktivnost) { this.aktivnost = aktivnost; }

    public Set<Knjiga> getKnjige() { return knjige; }

    public void setKnjige(Set<Knjiga> knjige) { this.knjige = knjige; }
}
