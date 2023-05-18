package acs.uns.ac.rs.webproject.dto;

public class AddShelfDto {
    private String name;

    private boolean isPrimary;

    private long userId;
    public AddShelfDto() {
    }


    public AddShelfDto(String name, boolean isPrimary) {
        this.name = name;
        this.isPrimary = isPrimary;
    }

    public AddShelfDto(String name, boolean isPrimary, int userId) {
        this.name = name;
        this.isPrimary = isPrimary;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
