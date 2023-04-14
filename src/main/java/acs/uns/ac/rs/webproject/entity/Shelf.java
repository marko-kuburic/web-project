package acs.uns.ac.rs.webproject.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @ManyToMany
    @JoinTable(name = "shelves_and_items",
            joinColumns = @JoinColumn(name = "shelf_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private Set<ShelfItem> items = new HashSet<ShelfItem>();

    public Shelf() {
    }

    public Shelf(String name, Boolean isPrimary) {
        this.name = name;
        this.isPrimary = isPrimary;
    }

    public Shelf(String name, Boolean isPrimary, Set<ShelfItem> items) {
        this.name = name;
        this.isPrimary = isPrimary;
        this.items = items;
    }



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

    public Set<ShelfItem> getItems() {
        return items;
    }

    public void setItems(Set<ShelfItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "name='" + name + '\'' +
                '}';
    }
}
