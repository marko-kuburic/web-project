package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Korisnik;

import java.util.Date;

public class UpdateRecDto {
    private Long id;
    private int ocena;
    private String tekst;
    private Date datum;
    private Korisnik korisnik;

    public UpdateRecDto(Long id, int ocena, String tekst, Date datum, Korisnik korisnik) {
        this.id = id;
        this.ocena = ocena;
        this.tekst = tekst;
        this.datum = datum;
        this.korisnik = korisnik;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getOcena() { return ocena; }

    public void setOcena(int ocena) { this.ocena = ocena; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public Date getDatum() { return datum; }

    public void setDatum(Date datum) { this.datum = datum; }

    public Korisnik getKorisnik() { return korisnik; }

    public void setKorisnik(Korisnik korisnik) { this.korisnik = korisnik; }
}
