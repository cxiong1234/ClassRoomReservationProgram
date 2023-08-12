package models;

public interface IRoom {
    String getRoomNumber();
    Double getPrice();
    RoomType getRoomType();

    boolean getIsFree();
}
