import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates centralized inventory management using HashMap.
 */

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

    public void updateAvailability(String roomType, int count) {

        inventory.put(roomType, count);
    }

    public void displayInventory() {

        System.out.println("\n------ CURRENT ROOM INVENTORY ------");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("             BOOK MY STAY APP                ");
        System.out.println("     Centralized Room Inventory System       ");
        System.out.println("                Version 3.0                  ");
        System.out.println("=============================================");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability for Double Room...");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        System.out.println("\nUpdating Suite Room availability...");

        inventory.updateAvailability("Suite Room", 4);

        inventory.displayInventory();
    }
}