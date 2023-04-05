package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.entity.User;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Author extends User implements Serializable{

    @Column
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "Authors&Books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> bookSet = new HashSet<>();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }


}
