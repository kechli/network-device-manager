import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    // 1. Αποθήκευση νέας συσκευής (CREATE)
    public void saveDevice(Device device) {
        String sql = "INSERT INTO devices(name, ip, type, is_online, ports) VALUES(?, ?, ?, ?, ?);";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, device.getName());
            pstmt.setString(2, device.getIpAddress());
            pstmt.setString(3, device.getType()); // <-- Αλλαγή από getType() σε getDeviceType()
            pstmt.setInt(4, device.isOnline() ? 1 : 0);
            pstmt.setInt(5, device.getPortCount());

            pstmt.executeUpdate();
            System.out.println("[DB] Saved device: " + device.getName());

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }
    }

    // 2. Ανάγνωση όλων των συσκευών (READ)
    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices;";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String ip = rs.getString("ip");
                String type = rs.getString("type");
                boolean isOnline = rs.getInt("is_online") == 1;
                int ports = rs.getInt("ports");

                Device device;
                if ("Router".equalsIgnoreCase(type)) {
                    device = new Router(id, name, ip, isOnline, ports);
                } else {
                    device = new Switch(id, name, ip, isOnline, ports);
                }

                devices.add(device);
            }

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }

        return devices;
    }

    // 3. Ενημέρωση κατάστασης συσκευής (UPDATE)
    public void updateDeviceStatus(int id, boolean isOnline) {
        String sql = "UPDATE devices SET is_online = ? WHERE id = ?;";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, isOnline ? 1 : 0);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            System.out.println("[DB] Device ID " + id + " status updated.");

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }
    }

    // 4. Διαγραφή συσκευής (DELETE)
    public void deleteDevice(int id) {
        String sql = "DELETE FROM devices WHERE id = ?;";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("[DB] Device ID " + id + " deleted.");

        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }
    }
}