package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.ZahtevZaAktivacijuNalogaAutora;

public class ZahtevDto {
    private String mail;
    private String brojTelefona;
    private String dodatnaPoruka;

    private Long korisnik_id;

    public ZahtevDto(String mail, String brojTelefona, String dodatnaPoruka, Long korisnik_id) {
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.dodatnaPoruka = dodatnaPoruka;
        this.korisnik_id = korisnik_id;
    }
    public ZahtevDto(ZahtevZaAktivacijuNalogaAutora zana) {
        this.mail = zana.getEmail();
        this.brojTelefona = zana.getTelefon();
        this.dodatnaPoruka = zana.getPoruka();
    }

    public ZahtevDto() {
    }

    public Long getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(Long korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getDodatnaPoruka() {
        return dodatnaPoruka;
    }

    public void setDodatnaPoruka(String dodatnaPoruka) {
        this.dodatnaPoruka = dodatnaPoruka;
    }
}
