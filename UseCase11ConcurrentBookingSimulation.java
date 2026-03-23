import java.util.*;

/**
 * Book My Stay App
 * Use Case 11: Concurrent Booking Simulation
 *
 * Demonstrates thread-safe booking using synchronization.
 *
 * @author nandinipatni
 * @version 11.0
 */

/* -------- Reservation -------- */
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

/* -------- Booking Queue -------- */
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

/* -------- Room Inventory -------- */
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public synchronized boolean isAvailable(String type) {
        return rooms.getOrDefault(type, 0) > 0;
    }

    public synchronized void decrease(String type) {
        rooms.put(type, rooms.get(type) - 1);
    }

    public synchronized void display() {
        System.out.println("\nRemaining Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

/* -------- Allocation Service -------- */
class RoomAllocationService {

    private Map<String, Integer> counters = new HashMap<>();

    public synchronized void allocateRoom(Reservation r, RoomInventory inventory) {

        if (!inventory.isAvailable(r.roomType)) {
            System.out.println("No rooms available for " + r.guestName);
            return;
        }

        inventory.decrease(r.roomType);

        int count = counters.getOrDefault(r.roomType, 0) + 1;
        counters.put(r.roomType, count);

        String roomId = r.roomType + "-" + count;

        System.out.println("Booking confirmed for Guest: " +
                r.guestName + ", Room ID: " + roomId);
    }
}

/* -------- Concurrent Processor -------- */
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private RoomAllocationService service;

    public ConcurrentBookingProcessor(
            BookingRequestQueue queue,
            RoomInventory inventory,
            RoomAllocationService service) {

        this.queue = queue;
        this.inventory = inventory;
        this.service = service;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // Critical section 1 (queue)
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.getRequest();
            }

            // Critical section 2 (inventory + allocation)
            synchronized (inventory) {
                service.allocateRoom(r, inventory);
            }
        }
    }
}

/* -------- MAIN -------- */
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Add requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Double"));
        queue.addRequest(new Reservation("Kural", "Suite"));
        queue.addRequest(new Reservation("Subha", "Single"));

        // Threads
        Thread t1 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        inventory.display();
    }
}