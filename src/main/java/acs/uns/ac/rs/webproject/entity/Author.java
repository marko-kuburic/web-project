package acs.uns.ac.rs.webproject.entity;

import acs.uns.ac.rs.webproject.dto.AuthorDto;
import acs.uns.ac.rs.webproject.entity.User;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Author extends User implements Serializable{

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "Authors&Books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> bookSet = new HashSet<>();

    public Author() {
    }

    public Author(String name, String surname, String username, String mail, String password, LocalDate birthDate, Role role, boolean isActive) {
        super(name, surname, username, mail, password, birthDate, role);
        this.isActive = isActive;
    }

    public Author(String name, String surname, String username, String mail, String password, LocalDate birthDate, Role role, Set<Review> reviews, boolean isActive) {
        super(name, surname, username, mail, password, birthDate, role, reviews);
        this.isActive = isActive;
    }

    public Author(AuthorDto a) {
        super(a);
        this.isActive = a.isActive();
    }

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

    public boolean addBook(Book book)
    {
        return bookSet.add(book);
    }
    @Override
    public String toString() {
        return "Author{" +
                super.toString() +
                "isActive=" + isActive +
                '}';
    }
}
