import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only access to centralized inventory
 * and filtering of available room types.
 *
 * @author nandinipatni
 * @version 4.0
 */

/* -------- Room Domain Model -------- */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : $" + price);
    }

    public String getRoomType() {
        return roomType;
    }
}

/* -------- Concrete Room Types -------- */

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

/* -------- Inventory -------- */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/* -------- Search Service -------- */

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchRooms(Room[] rooms) {

        System.out.println("\n------ AVAILABLE ROOMS ------\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println();
            }
        }
    }
}

/* -------- Application Entry -------- */

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("          BOOK MY STAY APP                 ");
        System.out.println("         Room Search Service               ");
        System.out.println("             Version 4.0                   ");
        System.out.println("===========================================");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
            new SingleRoom(),
            new DoubleRoom(),
            new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchRooms(rooms);
    }
}