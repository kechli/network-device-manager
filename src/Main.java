import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Αρχικοποίηση της βάσης
        DatabaseManager.initializeDatabase();

        // Εκκίνηση του Swing GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}