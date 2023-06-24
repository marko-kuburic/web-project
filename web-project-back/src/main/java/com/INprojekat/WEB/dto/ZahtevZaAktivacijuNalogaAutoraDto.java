package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.ZahtevZaAktivacijuNalogaAutora;

import java.util.Date;

public class ZahtevZaAktivacijuNalogaAutoraDto {
    private Long id;
    private String email;
    private String telefon;
    private String poruka;
    private Date datum;
    private ZahtevZaAktivacijuNalogaAutora.Status status;
    private Korisnik korisnik;

    public ZahtevZaAktivacijuNalogaAutoraDto(Long id, String email, String telefon, String poruka, Date datum, ZahtevZaAktivacijuNalogaAutora.Status status, Korisnik korisnik) {
        this.id = id;
        this.email = email;
        this.telefon = telefon;
        this.poruka = poruka;
        this.datum = datum;
        this.status = status;
        this.korisnik = korisnik;
    }

    public ZahtevZaAktivacijuNalogaAutoraDto(ZahtevZaAktivacijuNalogaAutora zana) {
        this.id = zana.getId();
        this.email = zana.getEmail();
        this.telefon = zana.getTelefon();
        this.poruka = zana.getPoruka();
        this.datum = zana.getDatum();
        this.status = zana.getStatus();
        this.korisnik = zana.getKorisnik();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public ZahtevZaAktivacijuNalogaAutora.Status getStatus() {
        return status;
    }

    public void setStatus(ZahtevZaAktivacijuNalogaAutora.Status status) {
        this.status = status;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
