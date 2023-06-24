package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.entity.StavkaPolice;

public class StavkaPoliceAddDto {
    private Long id;
    private Recenzija recenzija;
    private Knjiga knjiga;

    public StavkaPoliceAddDto(Long id, Recenzija recenzija, Knjiga knjiga) {
        this.id = id;
        this.recenzija = recenzija;
        this.knjiga = knjiga;
    }

    public StavkaPoliceAddDto(StavkaPolice stavkaPolice) {
        this.id = stavkaPolice.getId();
        this.recenzija = stavkaPolice.getRecenzija();
        this.knjiga = stavkaPolice.getKnjiga();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
}
