package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.entity.StavkaPolice;

import java.util.HashSet;
import java.util.Set;

public class PolicaDto {
    private Long id;
    private String naziv;
    private boolean primarna;
    private Set<StavkaPolice> stavkePolica = new HashSet<>();

    public PolicaDto(Long id, String naziv, boolean primarna, Set<StavkaPolice> stavkePolica) {
        this.id = id;
        this.naziv = naziv;
        this.primarna = primarna;
        this.stavkePolica = stavkePolica;
    }

    public PolicaDto(Polica polica) {
        this.id = polica.getId();
        this.naziv = polica.getNaziv();
        this.primarna = polica.isPrimarna();
        this.stavkePolica = polica.getStavkePolica();
    }

    public PolicaDto(){
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

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<StavkaPolice> getStavkePolica() {
        return stavkePolica;
    }

    public void setStavkePolica(Set<StavkaPolice> stavkePolica) {
        this.stavkePolica = stavkePolica;
    }
}
