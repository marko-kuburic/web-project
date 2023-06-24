package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Zanr;
import jakarta.persistence.Column;

import java.util.HashSet;
import java.util.Set;

public class ZanrDto {
    private Long id;
    private String naziv;
    public ZanrDto(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }
    public ZanrDto(Zanr zanr) {
        this.id = zanr.getId();
        this.naziv = zanr.getNaziv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
