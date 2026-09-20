public class Switch extends Device implements Pingable {
    private int portCount;

    public Switch(String name, String ipAddress, int portCount) {
        super(name, ipAddress);
        this.portCount = portCount;
    }

    @Override
    public String getDeviceType() {
        return "Switch";
    }

    public int getPortCount() {
        return portCount;
    }

    // Υλοποίηση της μεθόδου του Interface Pingable
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
}