import java.util.*;

/**
 * Book My Stay App
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates attaching optional services to a reservation
 * using Map<String, List<Service>>.
 *
 * @author nandinipatni
 * @version 7.0
 */

class Service {

    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());

        servicesByReservation.get(reservationId).add(service);

        System.out.println("Added service: " + service.getServiceName());
    }

    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("         BOOK MY STAY APP                  ");
        System.out.println("      Add-On Service Selection             ");
        System.out.println("             Version 7.0                   ");
        System.out.println("===========================================");

        String reservationId = "SingleRoom-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Spa", 1000));

        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("\nReservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}