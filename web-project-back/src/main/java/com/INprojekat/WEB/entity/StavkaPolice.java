package com.INprojekat.WEB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "STAVKE")
public class StavkaPolice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recenzija_id")
    private Recenzija recenzija;

    @ManyToOne
    @JoinColumn(name="knjiga_id")
    private Knjiga knjiga;

    public Recenzija getRecenzija() { return recenzija; }

    public void setRecenzija(Recenzija recenzija) { this.recenzija = recenzija; }

    public Knjiga getKnjiga() { return knjiga; }

    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
