package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

public class ShelfDto {

    private Long id;

    private String name;
    private Boolean isPrimary;
    private User user;

    private Set<ShelfItem> items = new HashSet<ShelfItem>();

    public ShelfDto(Long id, String name, Boolean isPrimary) {
        this.id = id;
        this.name = name;
        this.isPrimary = isPrimary;
    }

    public ShelfDto(Shelf shelf)
    {
        this.id = shelf.getId();
        this.name = shelf.getName();
        this.isPrimary = shelf.getPrimary();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
