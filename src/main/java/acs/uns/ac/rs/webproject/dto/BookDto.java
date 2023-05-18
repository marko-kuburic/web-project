package acs.uns.ac.rs.webproject.dto;

public class BookDto {

    long id;
    long shelfId;
    long shelfItemId;
    public BookDto() {
    }

    public BookDto(long id, long shelfItemId) {
        this.id = id;
        this.shelfItemId = shelfItemId;
    }

    public long getShelfItemId() {
        return shelfItemId;
    }

    public void setShelfItemId(long shelfItemId) {
        this.shelfItemId = shelfItemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShelfId() {
        return shelfId;
    }

    public void setShelfId(long shelfId) {
        this.shelfId = shelfId;
    }
}
