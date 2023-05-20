package acs.uns.ac.rs.webproject.dto;

import java.time.LocalDate;

public class AuthorDto {

    private long userId;
    private String name;

    private String surname;

    private String image;

    private String aboutMe;

    private LocalDate birthday;

    private String mail;

    private String pass;

    private boolean isActive;
    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, String image, String aboutMe, LocalDate birthday, String mail, String pass) {
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mail = mail;
        this.pass = pass;
    }

    public AuthorDto(String name, String surname, String image, String aboutMe, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
    }

    public AuthorDto(String name, String surname, String image, String aboutMe, LocalDate birthday, String mail, String pass, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mail = mail;
        this.pass = pass;
        this.isActive = isActive;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
