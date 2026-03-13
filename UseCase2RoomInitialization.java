/**
 * Book My Stay App
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Demonstrates abstraction, inheritance, polymorphism, and encapsulation.
 *
 * @author nandinipatni
 * @version 2.0
 */

abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type        : " + roomType);
        System.out.println("Number of Beds   : " + numberOfBeds);
        System.out.println("Price Per Night  : $" + pricePerNight);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 100.0);
    }

}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 180.0);
    }

}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 350.0);
    }

}

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("              BOOK MY STAY APP                    ");
        System.out.println("      Hotel Booking Management System             ");
        System.out.println("                  Version 2.0                     ");
        System.out.println("==================================================");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("\n------ ROOM DETAILS ------\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + singleRoomAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + doubleRoomAvailability);
        System.out.println();

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + suiteRoomAvailability);

        System.out.println("\nSystem execution completed successfully.");
    }
}