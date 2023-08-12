package api;

import models.Customer;
import models.Reservation;
import service.CustomerService;
import service.ReservationService;
import models.IRoom;
import models.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;


public class HotelResource {
    private CustomerService customerServiceReference = CustomerService.getInstance();
    private ReservationService reservationServiceReference = ReservationService.getInstance();
    private static HotelResource singletonReference = new HotelResource();


    private HotelResource() {
        //constructor
    }

    public static HotelResource getInstance() {
        if (singletonReference == null) {
            singletonReference = new HotelResource();
        }
        return singletonReference;
    }

    //implement functions in the CustomerService
    public Customer getCustomer(String customerEmail) {
        return customerServiceReference.getCustomer(customerEmail);
    }

    public void createACustomer(String firstName, String lastName, String customerEmail) {
        customerServiceReference.addCustomer(firstName, lastName, customerEmail);
    }

    //implement functions in the ReservationService
    public IRoom getRoom(String roomNumber) {
        return reservationServiceReference.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationServiceReference.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerServiceReference.getCustomer(customerEmail);
        if (customer == null) {
            return new ArrayList<>();
        }
        return reservationServiceReference.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationServiceReference.findRooms(checkInDate, checkOutDate);
    }

}