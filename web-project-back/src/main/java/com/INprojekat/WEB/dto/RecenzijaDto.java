package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Recenzija;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class RecenzijaDto {
    private Long id;
    private int ocena;
    private String tekst;
    private Date datum;
    private KorisnikRecenzijaDto korisnik;

    public RecenzijaDto() {
    }

    public RecenzijaDto(Long id, int ocena, String tekst, Date datum, KorisnikRecenzijaDto korisnik) {
        this.id = id;
        this.ocena = ocena;
        this.tekst = tekst;
        this.datum = datum;
        this.korisnik = korisnik;
    }

    public RecenzijaDto(Recenzija recenzija) {
        this.id = recenzija.getId();
        this.ocena = recenzija.getOcena();
        this.tekst = recenzija.getTekst();
        this.datum = recenzija.getDatum();
        this.korisnik = new KorisnikRecenzijaDto(recenzija.getKorisnik());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public KorisnikRecenzijaDto getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikRecenzijaDto korisnik) {
        this.korisnik = korisnik;
    }
}
