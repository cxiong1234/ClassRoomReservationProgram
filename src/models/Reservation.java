package models;

import java.util.Date;
import models.IRoom;
import models.Customer;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate ){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public IRoom getRoom(){
        return this.room;
    }
    public void setRoom(IRoom room){
        this.room = room;
    }
    public Date getCheckInDate(){
        return this.checkInDate;
    }
    public Date getCheckOutDate(){
        return this.checkOutDate;
    }

    @Override
    public String toString() {
        return "Customer info:" + customer.toString() + "\n" +
                "Room info:" + room.toString() + "\n" +
                "Check in Date:" + checkInDate + "\n" +
                "Check out Date:" + checkOutDate + "\n" ;
    }

}
