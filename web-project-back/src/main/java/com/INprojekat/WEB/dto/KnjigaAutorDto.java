package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KnjigaAutorDto {
    private String naslov;
    private String naslovnaFotografija;
    private String isbn;
    private int brojStrana;
    private Date datumObjavljivanja;
    private String opis;
    private Long zanrId;
    private Long autorId;

    public KnjigaAutorDto(String naslov, String naslovnaFotografija, String ISBN, int brojStrana, Date datumObjavljivanja, String opis, Long autorId, Long zanrId) {
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.isbn = ISBN;
        this.brojStrana = brojStrana;
        this.datumObjavljivanja = datumObjavljivanja;
        this.opis = opis;
        this.autorId = autorId;
        this.zanrId = zanrId;
    }
    public KnjigaAutorDto(Knjiga knjiga) {
        this.naslov = knjiga.getNaslov();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
        this.isbn = knjiga.getISBN();
        this.brojStrana = knjiga.getBrojStrana();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.opis = knjiga.getOpis();
        this.autorId = knjiga.getAutor().getId();
        this.zanrId = knjiga.getZanr().getId();
    }
    public KnjigaAutorDto() {
    }

    public String getNaslov() {
        return naslov;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
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

    public int getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(int brojStrana) {
        this.brojStrana = brojStrana;
    }

    public Date getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(Date datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
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
