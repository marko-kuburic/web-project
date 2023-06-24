package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Zanr;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class UpdateKnjigaDto {
    private String naslov;
    private String naslovnaFotografija;
    private String isbn;
    private Date datumObjavljivanja;
    private int brojStrana;
    private String opis;
    private Long autorId;
    private Long zanrId;

    public UpdateKnjigaDto(String naslov, String naslovnaFotografija, String isbn, Date datumObjavljivanja, int brojStrana, String opis, Long autorId, Long zanrId) {
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.isbn = isbn;
        this.datumObjavljivanja = datumObjavljivanja;
        this.brojStrana = brojStrana;
        this.opis = opis;
        this.autorId = autorId;
        this.zanrId = zanrId;
    }

    public UpdateKnjigaDto() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }

    public Date getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(Date datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public int getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(int brojStrana) {
        this.brojStrana = brojStrana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getZanrId() {
        return zanrId;
    }

    public void setZanrId(Long zanrId) {
        this.zanrId = zanrId;
    }
}
