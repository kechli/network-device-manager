import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Δημιουργία δυναμικής λίστας για συσκευές
        ArrayList<Device> devices = new ArrayList<>();

        // Προσθήκη συσκευών στη λίστα
        devices.add(new Device("Core Router", "192.168.1.1"));
        devices.add(new Device("Access Switch", "192.168.1.2"));
        devices.add(new Device("Firewall", "192.168.1.254"));

        // Αλλάζουμε την κατάσταση της πρώτης συσκευής (Core Router) σε online
        devices.get(0).setOnline(true);

        System.out.println("--- NETWORK DEVICE INVENTORY ---");
        
        // Εκτύπωση λεπτομερειών για κάθε συσκευή στη λίστα
        for (Device dev : devices) {
            dev.printDetails();
        }
        int onlineCounter = 0;

        for (Device dev : devices) {
            if (dev.isOnline()) {
                onlineCounter++;
            }
        }
        System.out.println("Total online devices: " + onlineCounter);
    }
    
}