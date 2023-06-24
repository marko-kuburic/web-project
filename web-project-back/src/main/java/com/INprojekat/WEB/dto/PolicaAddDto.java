package com.INprojekat.WEB.dto;

public class PolicaAddDto {
    private String naziv;
    private boolean primarna;

    public PolicaAddDto(String naziv, boolean primarna) {
        this.naziv = naziv;
        this.primarna = primarna;
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }
}
