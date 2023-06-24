package com.INprojekat.WEB.dto;

public class ZanrAddDto {
    private String naziv;

    public ZanrAddDto(String naziv) {
        this.naziv = naziv;
    }
    public ZanrAddDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
