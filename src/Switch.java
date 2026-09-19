public class Switch extends Device {
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
}