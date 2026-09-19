public class Router extends Device {
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

    @Override
    public void printDetails() {
        String status = isOnline() ? "ONLINE" : "OFFLINE";
        System.out.println(getDeviceType() + ": " + getName() + " | IP: " + getIpAddress() + " | Ports: " + portCount + " | Status: " + status);
    }
}