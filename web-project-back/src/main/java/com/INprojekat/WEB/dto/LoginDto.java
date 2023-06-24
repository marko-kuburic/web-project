package com.INprojekat.WEB.dto;

public class LoginDto {
    private String mail;

    private String lozinka;

    public LoginDto() {
    }

    public LoginDto(String mail, String lozinka) {
        this.mail = mail;
        this.lozinka = lozinka;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
