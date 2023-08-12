package api;

import models.Room;
import models.RoomType;
import service.CustomerService;
import service.ReservationService;
import models.Customer;
import models.IRoom;
import java.util.List;
import java.util.Collection;

public class AdminResource {
    private CustomerService customerServiceReference = CustomerService.getInstance();
    private ReservationService reservationServiceReference = ReservationService.getInstance();

    private static AdminResource singletonReference = new AdminResource();

    private AdminResource() {
        //constructor
    }
    public static AdminResource getInstance() {
        if (singletonReference == null) {
            singletonReference = new AdminResource();
        }
        return singletonReference;
    }

    public Customer getCustomer(String email){
        return customerServiceReference.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms){
            reservationServiceReference.addRoom(room);
        } //end for
    }

    public void addOneRoom(String roomNumber, RoomType roomType, Double roomPrice){
        IRoom room = new Room(roomNumber, roomType, roomPrice);
        reservationServiceReference.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
    return reservationServiceReference.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerServiceReference.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationServiceReference.displayAllReservations();
    }
}
