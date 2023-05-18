package acs.uns.ac.rs.webproject.dto;

public class DeleteShelfDto {

    private long id;

    private long userID;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeleteShelfDto(long id) {
        this.id = id;
    }

    public DeleteShelfDto() {
    }

    public DeleteShelfDto(long id, long userID) {
        this.id = id;
        this.userID = userID;
    }
}
