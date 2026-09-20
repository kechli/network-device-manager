import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Device> devices = new ArrayList<>();

        devices.add(new Switch("Access Switch", "192.168.1.2", 48));
        devices.add(new Switch("Core Switch", "192.168.1.3", 24));
        devices.add(new Router("Core Router", "192.168.1.1", 8));

        // Αλλάζουμε την κατάσταση του Core Router σε ONLINE
        devices.get(2).setOnline(true);

        System.out.println("--- NETWORK DEVICE INVENTORY ---");
        for (Device dev : devices) {
            dev.printDetails();
        }

        System.out.println("\n--- NETWORK DIAGNOSTICS (PING TEST) ---");
        for (Device dev : devices) {
            // Ελέγχουμε αν η συσκευή υλοποιεί το Interface Pingable
            if (dev instanceof Pingable) {
                Pingable pingableDevice = (Pingable) dev;
                pingableDevice.ping();
            }
        }
    }
}