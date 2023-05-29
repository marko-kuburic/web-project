package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.dto.AddShelfDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shelf")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


   /* @JoinTable(name = "shelves_and_items",
            joinColumns = @JoinColumn(name = "shelf_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))*/
    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL )
    private Set<ShelfItem> items = new HashSet<>();

    public Shelf() {
    }

    public Shelf(String name, Boolean isPrimary) {
        this.name = name;
        this.isPrimary = isPrimary;
    }

    public Shelf(AddShelfDto addShelfDto)
    {
        this.name = addShelfDto.getName();
        this.isPrimary = addShelfDto.isPrimary();
    }

    public Shelf(String name, Boolean isPrimary, Set<ShelfItem> items) {
        this.name = name;
        this.isPrimary = isPrimary;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean addItem(ShelfItem item)
    {
        return items.add(item);

    }

    public boolean removeItem(ShelfItem item)
    {
       return items.remove(item);
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPrimary=" + isPrimary +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
