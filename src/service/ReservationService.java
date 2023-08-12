package service;

import models.IRoom;
import models.Customer;
import models.Reservation;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.util.Collection;
import java.util.*;

public class ReservationService {
    private static ReservationService singletonReference = new ReservationService();
    private final HashMap<String, IRoom> allRoomsMap = new HashMap<>();
    private final HashMap<String, Collection<Reservation>> allReservationsMap = new HashMap<>(); // key is customer email, value is Collection<Reservation>,because a customer can have multiple reservations.


    private ReservationService(){
        //constructor
    }

    public static ReservationService getInstance(){
        if(singletonReference==null){
            singletonReference = new ReservationService();
        }
        return singletonReference;
    }


    public void addRoom(IRoom room){
        allRoomsMap.put(room.getRoomNumber(), room);
    }
    public IRoom getARoom(String roomId){
        return this.allRoomsMap.get(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return allRoomsMap.values();
    }

    public  Collection<Reservation> getCustomersReservation(Customer customer){
        return this.allReservationsMap.get(customer.getEmailAddress());
    }
    public Collection<Reservation> getAllCustomersReservation(){
        Collection<Reservation> allCustomersReservationValues = new ArrayList<Reservation>();
        for(Collection<Reservation> PerCustomerReservations: allReservationsMap.values()){
            allCustomersReservationValues.addAll(PerCustomerReservations);
        }
        return allCustomersReservationValues;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<Reservation> allReservations = getAllCustomersReservation();
        Set<Reservation> allReservationsSet = new HashSet<>(allReservations);
        Set<IRoom> occupiedRoomsSet =  new HashSet<>();

        //Getting all room set
        Collection<IRoom> allRooms = allRoomsMap.values();
        Set<IRoom> allRoomsSet = new HashSet<>(allRooms);

        for (Reservation reservation: allReservationsSet){
            if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                occupiedRoomsSet.add(reservation.getRoom());
            }
        } //end for

        allRoomsSet.removeAll(occupiedRoomsSet); // here is avail room set

        return allRoomsSet; // actually is the avail room set

    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection <Reservation> singleCustomerReservations = getCustomersReservation(customer);

        if(singleCustomerReservations == null){
            singleCustomerReservations = new HashSet<>();
            singleCustomerReservations.add(newReservation);
            allReservationsMap.put(customer.getEmailAddress(), singleCustomerReservations);
        }
        else{
            singleCustomerReservations.add(newReservation);
            allReservationsMap.put(customer.getEmailAddress(), singleCustomerReservations);
        }
        return newReservation;
    }


    public void displayAllReservations(){
        Collection<Reservation> allReservations = getAllCustomersReservation();
        Set<Reservation> allReservationsSet = new HashSet<>(allReservations);
        int reservationNum = allReservationsSet.size();
        if(reservationNum==0){
            System.out.println("#####No Reservations#####");
        }
        else{
            System.out.println("#####Reservations List#####");
            for(Reservation reservation: allReservationsSet){
                System.out.println(reservation);
                System.out.println("---------------------------");
            }
        }

    }
}
