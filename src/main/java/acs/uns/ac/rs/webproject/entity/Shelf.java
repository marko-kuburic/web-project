package acs.uns.ac.rs.webproject.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @OneToOne(mappedBy = "shelf")
    private ShelfItem item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public ShelfItem getItem() {
        return item;
    }

    public void setItem(ShelfItem item) {
        this.item = item;
    }
}
