import java.util.*;

/**
 * Book My Stay App
 * Use Case 8: Booking History & Reporting
 *
 * Demonstrates storing confirmed reservations
 * and generating reports from history.
 *
 * @author nandinipatni
 * @version 8.0
 */

/* -------- Reservation Class -------- */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/* -------- Booking History -------- */
class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/* -------- Booking Report Service -------- */
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report\n");

        List<Reservation> reservations = history.getConfirmedReservations();

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName() +
                               ", Room Type: " + r.getRoomType());
        }
    }
}

/* -------- Main Class -------- */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("        BOOK MY STAY APP                ");
        System.out.println("   Booking History & Reporting          ");
        System.out.println("           Version 8.0                  ");
        System.out.println("=========================================");

        BookingHistory history = new BookingHistory();

        // Adding confirmed reservations
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
    }
}