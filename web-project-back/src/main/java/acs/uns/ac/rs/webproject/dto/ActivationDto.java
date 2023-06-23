package acs.uns.ac.rs.webproject.dto;

import java.time.LocalDate;

import acs.uns.ac.rs.webproject.entity.Status;

public class ActivationDto {
    private Long id;

    private String mail;

    private String phoneNumber;

    private String message;

    private LocalDate date;

    private Status status;

    private String name;
    private String surname;
    private String username;
    private String password;
    private String password2;   
    private LocalDate birthDate;

    

    public ActivationDto(String mail, String phoneNumber, String message, LocalDate date, Status status, String name,
            String surname, String username, String password, String password2, LocalDate birthDate) {
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.date = date;
        this.status = status;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.birthDate = birthDate;
    }

    public ActivationDto() {
    }

    public ActivationDto(String mail, String phoneNumber, String message, LocalDate date, Status status) {
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
}
