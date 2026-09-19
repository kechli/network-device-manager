public class Main {
    public static void main(String[] args) {
        Device router = new Device("Core Router", "192.168.1.1");
        Device switchDevice = new Device("Access Switch", "192.168.1.2");

        // Αλλάζουμε την κατάσταση του router σε online
        router.setOnline(true);

        // Εκτύπωση στοιχείων με τη νέα μέθοδο printDetails
        router.printDetails();
        switchDevice.printDetails();
    }
}