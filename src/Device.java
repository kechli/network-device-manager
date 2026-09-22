public abstract class Device {
    protected int id;
    protected String name;
    protected String ipAddress;
    protected boolean isOnline;

    // Constructor με 4 παραμέτρους (συμπεριλαμβανομένου του id)
    public Device(int id, String name, String ipAddress, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.isOnline = isOnline;
    }

  // Constructor χωρίς id (χρήσιμο για δημιουργία νέας συσκευής πριν μπει στη βάση)
    public Device(String name, String ipAddress, boolean isOnline) {
        this(0, name, ipAddress, isOnline);
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

    public int getId() { 
        return id; 
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void printDetails() {
        String status = isOnline ? "ONLINE" : "OFFLINE";
        System.out.println(getDeviceType() + ": " + name + " | IP: " + ipAddress + " | Status: " + status);
    }
    // ΑΠΑΡΑΙΤΗΤΕΣ ABSTRACT ΜΕΘΟΔΟΙ
    public abstract String getType();
    public abstract int getPortCount();
}