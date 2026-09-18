public class Main {
    public static void main(String[] args) {
        // Δημιουργία αντικειμένων Device
        Device router = new Device("Core Router", "192.168.1.1");
        Device switchDevice = new Device("Access Switch", "192.168.1.2");

        // Εμφάνιση πληροφοριών
        System.out.println("Device 1 Name: " + router.getName());
        System.out.println("Device 1 IP: " + router.getIpAddress());

        System.out.println("---------------------------");

        System.out.println("Device 2 Name: " + switchDevice.getName());
        System.out.println("Device 2 IP: " + switchDevice.getIpAddress());
    }
}