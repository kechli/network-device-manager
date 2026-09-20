import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    // 1. Αποθήκευση συσκευής (INSERT)
    public static void insertDevice(Device device) {
        String sql = "INSERT INTO devices(name, ip_address, type, is_online, port_count) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, device.getName());
            pstmt.setString(2, device.getIpAddress());
            pstmt.setString(3, device.getDeviceType());
            pstmt.setInt(4, device.isOnline() ? 1 : 0);

            if (device instanceof Router) {
                pstmt.setInt(5, ((Router) device).getPortCount());
            } else if (device instanceof Switch) {
                pstmt.setInt(5, ((Switch) device).getPortCount());
            } else {
                pstmt.setInt(5, 0);
            }

            pstmt.executeUpdate();
            System.out.println("[DB] Saved device: " + device.getName());

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }
    }

    // 2. Ανάκτηση όλων των συσκευών (SELECT)
    public static List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String ip = rs.getString("ip_address");
                String type = rs.getString("type");
                boolean isOnline = rs.getInt("is_online") == 1;
                int portCount = rs.getInt("port_count");

                Device device = null;
                if ("Router".equalsIgnoreCase(type)) {
                    device = new Router(name, ip, portCount);
                } else if ("Switch".equalsIgnoreCase(type)) {
                    device = new Switch(name, ip, portCount);
                }

                if (device != null) {
                    device.setOnline(isOnline);
                    devices.add(device);
                }
            }

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }

        return devices;
    }
}