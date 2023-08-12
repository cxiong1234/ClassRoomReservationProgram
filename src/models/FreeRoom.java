package models;
import models.RoomType;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType roomType){
        super(roomNumber, roomType, 0.0);
    }

    @Override
    public String toString(){
        return "###This Room is Free###" + "\n" + super.toString();
    }
}
