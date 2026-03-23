import java.util.*;

/**
 * Book My Stay App
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Demonstrates safe cancellation and rollback using Stack.
 *
 * @author nandinipatni
 * @version 10.0
 */

/* -------- Room Inventory -------- */
class RoomInventory {

    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public void increaseAvailability(String roomType) {
        rooms.put(roomType, rooms.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }
}

/* -------- Cancellation Service -------- */
class CancellationService {

    private Map<String, String> reservationRoomMap;
    private Stack<String> rollbackStack;

    public CancellationService() {
        reservationRoomMap = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    // Register confirmed booking
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomMap.put(reservationId, roomType);
    }

    // Cancel booking and rollback
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation. Reservation not found.");
            return;
        }

        String roomType = reservationRoomMap.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increaseAvailability(roomType);

        // Remove booking
        reservationRoomMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    // Show rollback history
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (int i = rollbackStack.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + rollbackStack.get(i));
        }
    }
}

/* -------- Main Class -------- */
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate booking
        service.registerBooking("Single-1", "Single");

        // Cancel booking
        service.cancelBooking("Single-1", inventory);

        // Show rollback history
        service.showRollbackHistory();

        // Show updated inventory
        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getAvailability("Single"));
    }
}