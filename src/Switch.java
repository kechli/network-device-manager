public class Switch extends Device implements Pingable {
    private int portCount;

    public Switch(int id, String name, String ipAddress, boolean isOnline, int portCount) {
        super(id, name, ipAddress, isOnline);
        this.portCount = portCount;
    }

    public Switch(String name, String ipAddress, boolean isOnline, int portCount) {
        super(0, name, ipAddress, isOnline);
        this.portCount = portCount;
    }

    @Override
    public String getDeviceType() { // <-- Αλλαγή από getType σε getDeviceType
        return "Switch";
    }

    @Override
    public int getPortCount() {
        return portCount;
    }

    @Override
    public boolean ping() {
        if (isOnline()) {
            System.out.println("PING Success: " + getName() + " (" + getIpAddress() + ") responded in 1ms.");
            return true;
        } else {
            System.out.println("PING Failed: " + getName() + " (" + getIpAddress() + ") is unreachable.");
            return false;
        }
    }

    @Override
    public String getType() {
        return "Switch";
    }

    
}