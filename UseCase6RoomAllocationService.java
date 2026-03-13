import java.util.*;

/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates room allocation while preventing double booking
 * using Set and HashMap.
 *
 * @author nandinipatni
 * @version 6.0
 */


/* -------- Reservation -------- */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getGuestName() {
        return guestName;
    }
}


/* -------- Inventory -------- */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseAvailability(String roomType) {

        int current = inventory.get(roomType);

        if (current > 0) {
            inventory.put(roomType, current - 1);
        }
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}


/* -------- Booking Service -------- */

class BookingService {

    private RoomInventory inventory;
    private Set<String> allocatedRoomIDs;
    private HashMap<String, Set<String>> roomAllocations;
    private int roomCounter = 1;

    public BookingService(RoomInventory inventory) {

        this.inventory = inventory;
        allocatedRoomIDs = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    public void processBooking(Queue<Reservation> queue) {

        System.out.println("\nProcessing booking requests...\n");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();

            String roomType = request.getRoomType();

            if (inventory.getAvailability(roomType) > 0) {

                String roomID = roomType.replace(" ", "") + "-" + roomCounter++;

                allocatedRoomIDs.add(roomID);

                roomAllocations.putIfAbsent(roomType, new HashSet<>());
                roomAllocations.get(roomType).add(roomID);

                inventory.decreaseAvailability(roomType);

                System.out.println("Reservation Confirmed:");
                System.out.println("Guest : " + request.getGuestName());
                System.out.println("Room Type : " + roomType);
                System.out.println("Assigned Room ID : " + roomID);
                System.out.println();

            } else {

                System.out.println("Reservation Failed for "
                        + request.getGuestName()
                        + " (No rooms available for "
                        + roomType + ")");
                System.out.println();
            }
        }
    }
}


/* -------- Application Entry -------- */

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("         BOOK MY STAY APP                  ");
        System.out.println("      Room Allocation & Confirmation       ");
        System.out.println("             Version 6.0                   ");
        System.out.println("===========================================");

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("David", "Suite Room"));

        BookingService bookingService = new BookingService(inventory);

        bookingService.processBooking(bookingQueue);

        inventory.displayInventory();
    }
}