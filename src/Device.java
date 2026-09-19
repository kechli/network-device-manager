public class Device {
    private String name;
    private String ipAddress;
    private boolean isOnline; // Νέο πεδίο

    // Ενημερωμένος Constructor
    public Device(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.isOnline = false; // Προεπιλεγμένη τιμή: offline
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    // Νέα μέθοδος για εμφάνιση όλων των λεπτομερειών
    public void printDetails() {
        String status = isOnline ? "ONLINE" : "OFFLINE";
        System.out.println("Device: " + name + " | IP: " + ipAddress + " | Status: " + status);
    }
}