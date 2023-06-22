package acs.uns.ac.rs.webproject.dto;

import java.time.LocalDate;

public class AuthorDto extends UserDto{

    private boolean isActive;
    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, String image, String aboutMe, LocalDate birthday, String mail, String pass) {
        super(name, surname, image, aboutMe, birthday, mail, pass);
        this.isActive = false;
    }


    public AuthorDto(String name, String surname, String image, String aboutMe, LocalDate birthday, String mail, String pass, boolean isActive) {
        super(name, surname, image, aboutMe, birthday, mail, pass);
        this.isActive = isActive;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
