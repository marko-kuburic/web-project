package com.INprojekat.WEB.entity;

import com.INprojekat.WEB.dto.ZahtevZaAktivacijuNalogaAutoraDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ZAHTEV")
public class ZahtevZaAktivacijuNalogaAutora implements Serializable {

    public enum Status {na_cekanju, odobren, odbijen}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String telefon;
    @Column
    private String poruka;
    @Column
    private Date datum;
    @Column
    private Status status;
    @ManyToOne
    private Korisnik korisnik;

    public ZahtevZaAktivacijuNalogaAutora(Long id, String email, String telefon, String poruka, Date datum, Status status, Korisnik korisnik) {
        this.id = id;
        this.email = email;
        this.telefon = telefon;
        this.poruka = poruka;
        this.datum = datum;
        this.status = status;
        this.korisnik = korisnik;
    }
    public ZahtevZaAktivacijuNalogaAutora(ZahtevZaAktivacijuNalogaAutoraDto zanaDto) {
        this.id = zanaDto.getId();
        this.email = zanaDto.getEmail();
        this.telefon = zanaDto.getTelefon();
        this.poruka = zanaDto.getPoruka();
        this.datum = zanaDto.getDatum();
        this.status = zanaDto.getStatus();
        this.korisnik = zanaDto.getKorisnik();
    }
    public ZahtevZaAktivacijuNalogaAutora() {
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }

    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getPoruka() { return poruka; }

    public void setPoruka(String poruka) { this.poruka = poruka; }

    public Date getDatum() { return datum; }

    public void setDatum(Date datum) { this.datum = datum; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
