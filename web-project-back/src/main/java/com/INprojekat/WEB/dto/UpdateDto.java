package com.INprojekat.WEB.dto;

import java.util.Date;

public class UpdateDto {

    private  String ime;

    private String prezime;

    private String naslovnaFotografija;

    private String opis;

    private Date datumRodjenja;

    private String mail;

    private String lozinka;

    private String newlozinka;

    public UpdateDto() {
    }

    public UpdateDto(String ime, String prezime, String naslovnaFotografija, String opis, Date datumRodjenja, String mail, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.naslovnaFotografija = naslovnaFotografija;
        this.opis = opis;
        this.datumRodjenja = datumRodjenja;
        this.mail = mail;
        this.lozinka = lozinka;
    }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }

    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getNaslovnaFotografija() { return naslovnaFotografija; }

    public void setNaslovnaFotografija(String naslovnaFotografija) { this.naslovnaFotografija = naslovnaFotografija; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Date getDatumRodjenja() { return datumRodjenja; }

    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getLozinka() { return lozinka; }

    public void setLozinka(String lozinka) { this.lozinka = lozinka; }

    public String getNewlozinka() { return newlozinka; }

    public void setNewlozinka(String newlozinka) { this.newlozinka = newlozinka; }
}
