import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Αρχικοποίηση Βάσης
        DatabaseManager.initializeDatabase();

        // 2. Δημιουργία αντικειμένων
        Router r1 = new Router("Gateway Router", "192.168.1.1", 8);
        Switch s1 = new Switch("Main Switch", "192.168.1.2", 24);
        r1.setOnline(true);

        // 3. Αποθήκευση στη SQLite
        System.out.println("\n--- SAVING TO DATABASE ---");
        DeviceDAO.insertDevice(r1);
        DeviceDAO.insertDevice(s1);

        // 4. Ανάκτηση από τη SQLite
        System.out.println("\n--- READING FROM DATABASE ---");
        List<Device> loadedDevices = DeviceDAO.getAllDevices();

        for (Device dev : loadedDevices) {
            dev.printDetails();
        }
    }
}