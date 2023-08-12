package models;

import java.awt.datatransfer.FlavorEvent;
import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private RoomType roomType;
    private Double price;
    private boolean isfree;

    public Room (String roomNumber, RoomType roomType, Double price){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;

        if (this.price == 0.0){
            this.isfree = true;
        }
        else {
            this.isfree = false;
        }
    }
    public String getRoomNumber(){
        return this.roomNumber;
    }
    public RoomType getRoomType(){
        return this.roomType;
    }
    public Double getPrice(){
        return this.price;
    }

    public boolean getIsFree(){
        return this.isfree;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(getClass() != obj.getClass() || obj == null){
            return false;
        }

        Room room = (Room) obj;
        return this.roomNumber.equals(room.roomNumber);

    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }
    @Override
    public String toString(){
        return "Room Number: " + this.roomNumber + "\n" +
                "Room Type: " + this.roomType + "\n" +
                "Price: $"    + this.price + "\n";


    }
}
