import java.util.*;

/**
 * Book My Stay App
 * Use Case 9: Error Handling & Validation
 *
 * Demonstrates validation and custom exception handling
 * to ensure system reliability.
 *
 * @author nandinipatni
 * @version 9.0
 */

/* -------- Custom Exception -------- */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

/* -------- Room Inventory -------- */
class RoomInventory {

    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 2);
        rooms.put("Double", 1);
        rooms.put("Suite", 1);
    }

    public boolean isRoomAvailable(String roomType) {
        return rooms.containsKey(roomType) && rooms.get(roomType) > 0;
    }
}

/* -------- Validator -------- */
class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isRoomAvailable(roomType)) {
            throw new InvalidBookingException("Room type not available or invalid");
        }
    }
}

/* -------- Main Class -------- */
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("        BOOK MY STAY APP                ");
        System.out.println("      Error Handling & Validation       ");
        System.out.println("            Version 9.0                 ");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {

            System.out.print("Enter Guest Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Room Type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(name, roomType, inventory);

            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}