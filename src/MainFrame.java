import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private DeviceDAO deviceDAO;
    private JTable deviceTable;
    private DefaultTableModel tableModel;

    // Πεδία Φόρμας
    private JTextField nameField;
    private JTextField ipField;
    private JComboBox<String> typeBox;
    private JTextField portsField;

    public MainFrame() {
        deviceDAO = new DeviceDAO();

        // Ρυθμίσεις Παραθύρου
        setTitle("Network Device Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Κεντράρισμα στην οθόνη
        setLayout(new BorderLayout(10, 10));

        // 1. Πίνακας Προβολής Συσκευών (Κέντρο)
        String[] columnNames = {"ID", "Name", "IP Address", "Type", "Status", "Ports"};
        tableModel = new DefaultTableModel(columnNames, 0);
        deviceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(deviceTable);
        add(scrollPane, BorderLayout.CENTER);

        // 2. Φόρμα Εισαγωγής Συσκευής (Βορράς/Πάνω)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("IP:"));
        ipField = new JTextField(10);
        inputPanel.add(ipField);

        inputPanel.add(new JLabel("Type:"));
        typeBox = new JComboBox<>(new String[]{"Router", "Switch"});
        inputPanel.add(typeBox);

        inputPanel.add(new JLabel("Ports:"));
        portsField = new JTextField(5);
        inputPanel.add(portsField);

        add(inputPanel, BorderLayout.NORTH);

        // 3. Κουμπιά Ενεργειών (Νότος/Κάτω)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addButton = new JButton("Add Device");
        JButton refreshButton = new JButton("Refresh");
        JButton deleteButton = new JButton("Delete Selected");

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- Action Listeners (Λειτουργικότητα) ---

        // Φόρτωση δεδομένων
        refreshButton.addActionListener(e -> loadDevicesToTable());

        // Προσθήκη συσκευής
        addButton.addActionListener(e -> addDevice());

        // Διαγραφή συσκευής
        deleteButton.addActionListener(e -> deleteSelectedDevice());

        // Αρχική φόρτωση κατά την εκκίνηση
        loadDevicesToTable();
    }

    private void loadDevicesToTable() {
        tableModel.setRowCount(0); // Καθαρισμός πίνακα
        List<Device> devices = deviceDAO.getAllDevices();

        for (Device d : devices) {
            Object[] row = {
                d.getId(),
                d.getName(),
                d.getIpAddress(),
                d.getDeviceType(),
                d.isOnline() ? "ONLINE" : "OFFLINE",
                d.getPortCount()
            };
            tableModel.addRow(row);
        }
    }

    private void addDevice() {
        try {
            String name = nameField.getText().trim();
            String ip = ipField.getText().trim();
            String type = (String) typeBox.getSelectedItem();
            int ports = Integer.parseInt(portsField.getText().trim());

            if (name.isEmpty() || ip.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Device newDevice;
            if ("Router".equals(type)) {
                newDevice = new Router(0, name, ip, true, ports);
            } else {
                newDevice = new Switch(0, name, ip, true, ports);
            }

            deviceDAO.saveDevice(newDevice);
            loadDevicesToTable();

            // Καθαρισμός πεδίων
            nameField.setText("");
            ipField.setText("");
            portsField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ports must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedDevice() {
        int selectedRow = deviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a device to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete device ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            deviceDAO.deleteDevice(id);
            loadDevicesToTable();
        }
    }
}