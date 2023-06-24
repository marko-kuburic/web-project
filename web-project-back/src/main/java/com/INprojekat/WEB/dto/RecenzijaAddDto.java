package com.INprojekat.WEB.dto;

import java.util.Date;
import java.util.List;

public class RecenzijaAddDto {
    private int ocena;
    private Date datum;
    private List<Long> police;
    private String tekst;
    private Long korisnikId;

    private Long knjigaId;

    public RecenzijaAddDto(int ocena, Date datum, List<Long> police, String tekst, Long korisnikId, Long knjigaId) {
        this.ocena = ocena;
        this.datum = datum;
        this.police = police;
        this.tekst = tekst;
        this.korisnikId = korisnikId;
        this.knjigaId = knjigaId;
    }

    public RecenzijaAddDto() {}

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Long> getPolice() {
        return police;
    }

    public void setPolice(List<Long> police) {
        this.police = police;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getKnjigaId() { return knjigaId; }

    public void setKnjigaId(Long knjigaId) { this.knjigaId = knjigaId; }
}
