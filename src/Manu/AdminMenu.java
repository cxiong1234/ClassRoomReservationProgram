package Manu;
import models.Reservation;
import models.Customer;
import models.IRoom;
import models.RoomType;
import service.ReservationService;
import service.CustomerService;
import api.HotelResource;
import api.AdminResource;
import Manu.AdminMenu;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class AdminMenu {
    private static AdminResource adminResourceReference = AdminResource.getInstance();
    private static HotelResource hotelResourceReference = HotelResource.getInstance();

    public static void adminMenu(){
        displayAdminMenu();
        Scanner scanner = new Scanner(System.in);
        String adminChoice = null;

        do{
            adminChoice = scanner.nextLine();
            switch(adminChoice){
                case "1":
                    option1BrowseAllCustomers();
                    break;
                case "2":
                    option2BrowseAllRooms();
                    break;
                case "3":
                    option3BrowseAllReservations();
                    break;
                case "4":
                    option4AddARoom();
                    break;
                case "5":
                    MainMenu.displayWelcomeAndMainOption();
                    return;
                default:
                    System.out.println("Invalid Input! please type in 1-5.");
            }
        }while (adminChoice != "5");
    }

    private static void option1BrowseAllCustomers(){
        Collection <Customer> allCustomers = adminResourceReference.getAllCustomers();
        if(!allCustomers.isEmpty()){
            System.out.println("##############All Customers List############");

            for (Customer customer:allCustomers){
                System.out.println(customer);
                System.out.println("--------------------------------------------");
            }
        }
        else{
            System.out.println("###No Registered Customer Found!###");
        }
    }


    private static void option2BrowseAllRooms(){
        Collection <IRoom> allRooms = adminResourceReference.getAllRooms();
        if(!allRooms.isEmpty()){
            System.out.println("##############All Rooms List##############");
            for (IRoom room:allRooms){
                System.out.println(room);
                System.out.println("--------------------------------------------");
            }
        }
        else{
            System.out.println("#### No Room Found");
        }
    }


    private static void option3BrowseAllReservations(){
        adminResourceReference.displayAllReservations();
    }


    private static void option4AddARoom() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Input the Room Number: e.g. 203");
        String roomNum = scanner.nextLine();
        if (hotelResourceReference.getRoom(roomNum) != null) {
            System.out.println("This room already exist, will reset it");
        }

        String roomTypeNum = null;
        RoomType roomType = null;
        do {
            System.out.println("Please Input the Class Room Type: \n" +
                    "1. Lecture Room \n" + "2. Seminar Room \n" + "3. Interactive Class Room \n" + "4. Group Study Room");

            roomTypeNum = scanner.nextLine();

            switch (roomTypeNum) {
                case "1":
                    roomType = RoomType.LECTURE;
                    break;
                case "2":
                    roomType = RoomType.SEMINAR;
                    break;
                case "3":
                    roomType = RoomType.INTERACTIVE;
                    break;
                case "4":
                    roomType = RoomType.GROUPSTUDY;
                    break;
                default:
                    System.out.println("Unknown input, please type in 1-5");
                    break;
            }
        } while (roomType == null);


        Double roomPrice = null;
        do{
            System.out.println("Please Input Room Price: e.g. 179.2");
            try{
                roomPrice = scanner.nextDouble();
            }catch (Exception exp){
                System.out.println("Invalid Room Price value!");
            }
        }while(roomPrice == null);

        adminResourceReference.addOneRoom(roomNum, roomType, roomPrice);
        System.out.println("Room added!");


        Scanner scanner1 = new Scanner(System.in);

        String continueAdding = null;
        do {
            System.out.println("Continue adding room? reply: y/n");
            continueAdding = scanner1.nextLine();

            if (continueAdding.equals("y") || continueAdding.equals("Y")){
                continueAdding = "y";
            }
            else if(continueAdding.equals("n") || continueAdding.equals("N")){
                continueAdding = "n";
            }
            else{
                System.out.println("Invalid Input, please type in y/n");
            }
        } while (continueAdding == null);

        if(continueAdding=="y"){
        option4AddARoom();
        }
        else if(continueAdding == "n"){
            displayAdminMenu();
        }


    } // end option4 function




    private static void displayAdminMenu(){
        System.out.println("###############################################################");
        System.out.println("##################### Administrator Menu ######################");
        //System.out.println("###############################################################");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t          *       ");
        System.out.println("\t\t\t         * *       ");
        System.out.println("\t\t\t        * * *      ");
        System.out.println("\t\t\t       * * * *      ");
        System.out.println("\t\t\t        * * *      ");
        System.out.println("\t\t\t         * *       ");
        System.out.println("\t\t\t          *       ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                PRESS 1:    Browse All Customers");
        System.out.println("                PRESS 2:    Browse All Rooms");
        System.out.println("                PRESS 3:    Browse All Reservations");
        System.out.println("                PRESS 4:    Add A Room");
        System.out.println("                PRESS 5:    Back to Main Menu");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("###############################################################");
    }
}
