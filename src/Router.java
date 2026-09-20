public class Router extends Device implements Pingable {
    private int portCount;

    public Router(String name, String ipAddress, int portCount) {
        super(name, ipAddress);
        this.portCount = portCount;
    }

    @Override
    public String getDeviceType() {
        return "Router";
    }

    public int getPortCount() {
        return portCount;
    }

    public void setPortCount(int portCount) {
        this.portCount = portCount;
    }

    // Υλοποίηση της μεθόδου του Interface Pingable
    @Override
    public boolean ping() {
        if (isOnline()) {
            System.out.println("PING Success: " + getName() + " (" + getIpAddress() + ") responded in 2ms.");
            return true;
        } else {
            System.out.println("PING Failed: " + getName() + " (" + getIpAddress() + ") is unreachable.");
            return false;
        }
    }

    @Override
    public void printDetails() {
        String status = isOnline() ? "ONLINE" : "OFFLINE";
        System.out.println(getDeviceType() + ": " + getName() + " | IP: " + getIpAddress() + " | Ports: " + portCount + " | Status: " + status);
    }
}