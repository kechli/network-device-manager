public class Router extends Device implements Pingable {
    private int portCount;

    public Router(int id,String name, String ipAddress,boolean isOnline, int portCount) {
        super(id,name, ipAddress,isOnline);
        this.portCount = portCount;
    }

    public Router(String name, String ipAddress, boolean isOnline, int portCount) {
        super(0, name, ipAddress, isOnline);
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

    // Υλοποίηση Pingable
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

    // Υλοποίηση Configurable

    public void setHostname(String hostname) {
        System.out.println("[CONFIG] Router hostname changed from '" + getName() + "' to '" + hostname + "'");
    }

    public void updateIpAddress(String newIp) {
        System.out.println("[CONFIG] Updating Router IP from " + getIpAddress() + " to " + newIp);
    }

    @Override
    public void printDetails() {
        String status = isOnline() ? "ONLINE" : "OFFLINE";
        System.out.println(getDeviceType() + ": " + getName() + " | IP: " + getIpAddress() + " | Ports: " + portCount + " | Status: " + status);
    }

    @Override
    public String getType() {
        return "Router";
    }
}