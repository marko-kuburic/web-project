package acs.uns.ac.rs.webproject.dto;

import java.time.LocalDate;

import acs.uns.ac.rs.webproject.entity.Role;

public class AuthorDto extends UserDto{

    private boolean isActive;
    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, String username, String image, String aboutMe, LocalDate birthday, String mail, String pass, Role role) {
        super(name, surname, username, image, aboutMe, birthday, mail, pass, role);
        this.isActive = false;
    }


    public AuthorDto(String name, String surname, String username, String image, String aboutMe, LocalDate birthday, String mail, String pass, boolean isActive, Role role) {
        super(name, surname, username, image, aboutMe, birthday, mail, pass, role);
        this.isActive = isActive;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
