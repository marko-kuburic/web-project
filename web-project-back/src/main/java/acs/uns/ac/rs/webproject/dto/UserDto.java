package acs.uns.ac.rs.webproject.dto;

import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.User;

import java.time.LocalDate;

public class UserDto {

    private long userId;

    private String name;

    private String surname;

    private String username;

    private String image;

    private String aboutMe;

    private LocalDate birthday;

    private String mail;

    private String pass;

    private String passCheck;

    private Role role;

    public UserDto() {
    }

    public UserDto(String name, String surname, String username, String image, String aboutMe, LocalDate birthday, String mail, String pass, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mail = mail;
        this.pass = pass;
        this.role = role;
    }

    public UserDto(User user)
    {
        this.username = user.getUsername();
        this.userId = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.image = user.getImage();
        this.aboutMe = user.getBio();
        this.birthday = user.getBirthDate();
        this.mail = user.getMail();
        this.pass = user.getPassword();
        this.role = user.getRole();
    }

    public UserDto(long userId, String name, String surname, String username, String image, String aboutMe, LocalDate birthday, String mail, String pass) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mail = mail;
        this.pass = pass;
    }

    

    public UserDto(String name, String surname, String username, String image, String aboutMe, LocalDate birthday,
            String mail, String pass, String passCheck) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mail = mail;
        this.pass = pass;
        this.passCheck = passCheck;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDto(String name, String surname, String image, String aboutMe, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.aboutMe = aboutMe;
        this.birthday = birthday;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getPassCheck() {
        return passCheck;
    }

    public void setPassCheck(String passCheck) {
        this.passCheck = passCheck;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
