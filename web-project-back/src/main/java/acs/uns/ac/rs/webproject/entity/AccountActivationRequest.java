package acs.uns.ac.rs.webproject.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import acs.uns.ac.rs.webproject.dto.ActivationDto;

@Entity(name = "account_activation_request")
public class AccountActivationRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String message;

    @Column
    private LocalDate date;

    @Column
    private Status status;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private LocalDate birthDate;

    public AccountActivationRequest() {

    }

    
    public AccountActivationRequest(String mail, String phoneNumber, String message, LocalDate date, Status status,
            String name, String surname, String username, String password, LocalDate birthDate) {
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.date = date;
        this.status = status;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
    }

    public AccountActivationRequest(ActivationDto a) {
        this.mail = a.getMail();
        this.phoneNumber = a.getPhoneNumber();
        this.message = a.getMessage();
        this.date = a.getDate();
        this.status = Status.WAITING;
        this.name = a.getName();
        this.surname = a.getSurname();
        this.username = a.getUsername();
        this.password = a.getPassword();
        this.birthDate = a.getBirthDate();
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


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "AccountActivationRequest{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }


}
