package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KnjigaDto {
    private Long id;
    private String naslov;
    private String naslovnaFotografija;
    private String ISBN;
    private int brojStrana;
    private Date datumObjavljivanja;
    private String opis;
    private Double ocena;
    private Zanr zanr;
    private Set<StavkaPolice> stavka_polica = new HashSet<>();
    private Set<Recenzija> recenzije = new HashSet<>();
    private Autor autor;

    public KnjigaDto(Long id, String naslov, String naslovnaFotografija, String ISBN, Date datumObjavljivanja, int brojStrana, String opis, Double ocena, Zanr zanr, Set<Recenzija> recenzije, Autor autor) {
        this.id = id;
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.ISBN = ISBN;
        this.datumObjavljivanja = datumObjavljivanja;
        this.brojStrana = brojStrana;
        this.opis = opis;
        this.ocena = ocena;
        this.zanr = zanr;
        this.recenzije = recenzije;
        this.autor = autor;
    }

    public KnjigaDto(Knjiga knjiga) {
        this.id = knjiga.getId();
        this.naslov = knjiga.getNaslov();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
        this.ISBN = knjiga.getISBN();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.brojStrana = knjiga.getBrojStrana();
        this.opis = knjiga.getOpis();
        this.ocena = knjiga.getOcena();
        this.zanr = knjiga.getZanr();
        this.recenzije = knjiga.getRecenzije();
        this.autor = knjiga.getAutor();
    }

    public KnjigaDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public Date getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(Date datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public Set<StavkaPolice> getStavka_polica() {
        return stavka_polica;
    }

    public void setStavka_polica(Set<StavkaPolice> stavka_polica) {
        this.stavka_polica = stavka_polica;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    }
