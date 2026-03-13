import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Use Case 5: Booking Request Queue (First-Come-First-Served)
 *
 * Demonstrates fair booking request handling using a Queue.
 * Requests are stored in arrival order and processed using FIFO.
 *
 * @author nandinipatni
 * @version 5.0
 */

/* -------- Reservation Class -------- */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
    }
}

/* -------- Booking Request Queue -------- */

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("\nNew booking request added.");
        reservation.displayReservation();
    }

    public void displayRequests() {

        System.out.println("\n------ CURRENT BOOKING REQUEST QUEUE ------\n");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
            System.out.println();
        }
    }
}

/* -------- Application Entry -------- */

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("            BOOK MY STAY APP               ");
        System.out.println("      Booking Request Queue System         ");
        System.out.println("             Version 5.0                   ");
        System.out.println("===========================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        bookingQueue.displayRequests();
    }
}