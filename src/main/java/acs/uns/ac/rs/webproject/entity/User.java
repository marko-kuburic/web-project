package acs.uns.ac.rs.webproject.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "userr")
@DiscriminatorColumn(name="user_author", discriminatorType = DiscriminatorType.INTEGER)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String mail;

    @Column
    private String password;

    @Column( name = "birth_date")
    private LocalDate birthDate;

    @Column
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String username, String mail, String password, LocalDate birthDate, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public User(String name, String surname, String username, String mail, String password, LocalDate birthDate, Role role, Set<Review> reviews) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
        this.reviews = reviews;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate + '\'' +
                ", role=" + role +
                '}';
    }
}
