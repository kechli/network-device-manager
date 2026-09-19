public abstract class Device {
    private String name;
    private String ipAddress;
    private boolean isOnline;

    public Device(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.isOnline = false;
    }

    // Abstract μέθοδος: Κάθε υποκλάση ΠΡΕΠΕΙ να επιστρέφει τον τύπο της συσκευής
    public abstract String getDeviceType();

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void printDetails() {
        String status = isOnline ? "ONLINE" : "OFFLINE";
        System.out.println(getDeviceType() + ": " + name + " | IP: " + ipAddress + " | Status: " + status);
    }
}