public class Router extends Device {
    private int portCount; // Ειδικό χαρακτηριστικό μόνο για Routers

    // Constructor
    public Router(String name, String ipAddress, int portCount) {
        // Η super() καλεί τον constructor της πατέρας-κλάσης (Device)
        super(name, ipAddress);
        this.portCount = portCount;
    }

    public int getPortCount() {
        return portCount;
    }

    // Overriding: Επαναορίζουμε τη μέθοδο printDetails για να περιλαμβάνει και τις θύρες
    @Override
    public void printDetails() {
        String status = isOnline() ? "ONLINE" : "OFFLINE";
        System.out.println("Router: " + getName() + " | IP: " + getIpAddress() + " | Ports: " + portCount + " | Status: " + status);
    }
}