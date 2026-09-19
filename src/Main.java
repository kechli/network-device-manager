import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Device> devices = new ArrayList<>();

        // Χρησιμοποιούμε συγκεκριμένες υποκλάσεις (Router & Switch)
        devices.add(new Switch("Access Switch", "192.168.1.2", 48));
        devices.add(new Switch("Core Switch", "192.168.1.3", 24));
        devices.add(new Router("Core Router", "192.168.1.1", 8));

        // Αλλάζουμε την κατάσταση
        devices.get(2).setOnline(true);

        System.out.println("--- NETWORK DEVICE INVENTORY ---");
        
        for (Device dev : devices) {
            dev.printDetails();
        }

        int onlineCounter = 0;
        for (Device dev : devices) {
            if (dev.isOnline()) {
                onlineCounter++;
            }
        }

        System.out.println("\nTotal Online Devices: " + onlineCounter);
    }
}