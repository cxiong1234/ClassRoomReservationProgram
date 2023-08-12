package Manu;
import models.Reservation;
import models.Customer;
import models.IRoom;
import service.ReservationService;
import service.CustomerService;
import api.HotelResource;
import api.AdminResource;
import Manu.AdminMenu;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class MainMenu {
    private static HotelResource hotelResourceReference = HotelResource.getInstance();


    public static void mainMenu(){
        // construct the framework of main menu

    displayWelcomeAndMainOption();
    Scanner scanner = new Scanner(System.in);
    String customerChoice;
    do {
        customerChoice = scanner.nextLine();

            switch (customerChoice) {
                case "1":
                    option1BrowseAndReserveRoom();
                    break;
                case "2":
                    option2SeeMyReservations();
                    break;
                case "3":
                    option3CreateNewCustomerAccount();
                    break;
                case "4":
                    AdminMenu.adminMenu();
                    break;
                case "5":
                    System.out.println("Exiting the program!");
                    System.out.println("###############################################################");
                    System.out.println("###########     Thank You For Your Patronage!   ###############");
                    System.out.println("###############################################################");
                    return;
                default:
                    System.out.println("Unknown option, Please select option 1-5!");
                    break;


        }

    }while (customerChoice != "5") ;

    } // end of function


    // option 1
    private static void option1BrowseAndReserveRoom(){
        Date checkInDate;
        do {
            System.out.println("Please Enter Your Check In Date: MM-DD-YYYY:  (e.g. 03-11-2023)");
            checkInDate = DateparseUserInputDate();
        } while(checkInDate==null);

        Date checkOutDate;
        do {
            System.out.println("Please Enter Your Check Out Date: MM-DD-YYYY:  (e.g. 03-13-2023)");
            checkOutDate = DateparseUserInputDate();
        } while(checkOutDate==null);

       if(checkInDate.before(checkOutDate)){
            Collection <IRoom> availRooms = hotelResourceReference.findARoom(checkInDate, checkOutDate);

            if(availRooms.isEmpty()){
                System.out.println("No rooms found.");
            }
            else{
                displayAllRooms(availRooms);
                customerBookRoom(checkInDate, checkOutDate, availRooms);
            }
        }
        else{
            System.out.println("CheckOut Date before CheckIn, try again");
            option1BrowseAndReserveRoom();
        }
    }
    // this is a side function for option1
    private static Date DateparseUserInputDate(){
        Scanner scanner= new Scanner(System.in);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            String userInput = scanner.nextLine(); // tranfer the scanner into String
            Date date = formatter.parse(userInput);
            return date;
        }catch (Exception ex){
            System.out.println("Please Input An Valid Date!  \n Format:DD-MM-YYYY e.g. 03-11-2023");
            return null;
        }

    }

    // this is a side function for option 1
    // this function will take Collection of the Available rooms which get from option 1 function under the IN-OUT date range
    private static void customerBookRoom(Date checkInDate, Date checkOutDate, Collection<IRoom> AvailRooms) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Have you registered an account with us? type: y/n");
        String yesOrNoAccount = scanner.nextLine();
        if (yesOrNoAccount.equals("n") || yesOrNoAccount.equals("N")) {
            System.out.println("Please register an account, going back to main menu.");
            displayWelcomeAndMainOption();
        }
        else if (yesOrNoAccount.equals("Y") || yesOrNoAccount.equals("y")){


            System.out.println("Please type in your registered email address: e.g. xiaokeai@gmail.com");
            String emailAddress = scanner.nextLine();

            if (hotelResourceReference.getCustomer(emailAddress) == null){
                System.out.println("Your record was not found, going back to main manu.");
                displayWelcomeAndMainOption();
            }
            else{
                System.out.println("Please choose a room number to reserve:");
                String CustomerPickedRoomNum = scanner.nextLine();

                if(AvailRooms.stream().anyMatch(singleRoomInAvailRoom -> singleRoomInAvailRoom.getRoomNumber().equals(CustomerPickedRoomNum))){
                    IRoom customerPickedRoom = hotelResourceReference.getRoom(CustomerPickedRoomNum);
                    Reservation customerReservation = hotelResourceReference.bookARoom(emailAddress, customerPickedRoom, checkInDate, checkOutDate);
                    System.out.println("Congratulations, thank you for your booking!");
                    System.out.println("--------Confirmation of Booking-------------");
                    System.out.println(customerReservation);
                }
                else{
                    System.out.println("Room not available, going back to main menu");
                }
            }
            displayWelcomeAndMainOption();
        }
        else{
            System.out.println("### Invalid Response #### ");
            customerBookRoom(checkInDate, checkOutDate, AvailRooms);

        }
    } //end function
    // this is a side function for option1
    private static int displayAllRooms(Collection<IRoom> AllRooms){
        if (AllRooms.isEmpty()){
            System.out.println("##We could not found any available rooms##");
            return 0;
        }

        for (IRoom room : AllRooms){
            System.out.println(room);
        }
        return 0;
    }


    // option 2
    private static void option2SeeMyReservations(){
        System.out.println("Please Input Customer Email: (e.g. xiaokeai@gmail.com)");
        Scanner scanner = new Scanner(System.in);
        String emailAdd = scanner.nextLine();

        Customer customer = hotelResourceReference.getCustomer(emailAdd);
        if (customer == null){
            System.out.println("No Customer information found, please check your email!");
            return;
        }

        Collection<Reservation> allReservations = hotelResourceReference.getCustomersReservations(emailAdd);
        if(allReservations == null){
            System.out.println("The Customer haven't made any Reservations yet");
            return;
        }
        else{
            System.out.println("--------Reservations List------------");
            //allReservations.forEach(singleReservation -> System.out.println(singleReservation + "\n"));
            for (Reservation reservation: allReservations){
            System.out.println(reservation);
            System.out.println("-------------------------------------");
            }
        }

    } //end function



    // 3. option on the main menu: create the customer account
    private static void option3CreateNewCustomerAccount(){
        System.out.println("Please register your email address: e.g. xiaokeai@gmail.com");
        Scanner scanner = new Scanner(System.in);
        String newCustomerEmailAddress = scanner.nextLine();

        System.out.println("Please enter your LAST NAME:");
        String newCustomerLastName = scanner.nextLine();

        System.out.println("Please enter your FIRST NAME");
        String newCustomerFirstName = scanner.nextLine();

        //try to create new customer by the createACustomer method in the HotelResource method
        try{
            hotelResourceReference.createACustomer(newCustomerFirstName, newCustomerLastName, newCustomerEmailAddress);
            System.out.println("Congratulations, you've created a new account!");
            displayWelcomeAndMainOption();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            option3CreateNewCustomerAccount();
        }

    } //end function

    // option 4 go to admin menu




    public static void displayWelcomeAndMainOption(){
        System.out.println("###############################################################");
        System.out.println("####### Welcome to Xiong Library Room Reservation System #######");
        //System.out.println("###############################################################");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t  ^   ^  ");
        System.out.println("\t\t\t / \\_/ \\ ");
        System.out.println("\t\t\t    \u25B2");
        System.out.println("\t\t\t \\_____/");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                PRESS 1:    Room Reservation");
        System.out.println("                PRESS 2:    Find My Reservations");
        System.out.println("                PRESS 3:    Create My Account");
        System.out.println("                PRESS 4:    Switch To Admin Account");
        System.out.println("                PRESS 5:    Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("###############################################################");

    }
}
