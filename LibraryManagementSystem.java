import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryManagementSystem extends JFrame {

    // TABLE MODELS (GLOBAL)
    private DefaultTableModel bookModel;
    private DefaultTableModel memberModel;

    private int bookIdCounter = 1246;
    private int memberIdCounter = 4;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Dashboard", dashboardPanel());
        tabs.add("Books", booksPanel());
        tabs.add("Members", membersPanel());
        tabs.add("Reports", reportsPanel());

        add(tabs);
    }

    // ================= DASHBOARD =================
    private JPanel dashboardPanel() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel title = new JLabel("🏛️ LIBRARY MANAGEMENT SYSTEM - DASHBOARD");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        p.add(title, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(2,3,10,10));
        stats.add(stat("Total Books", "1,245"));
        stats.add(stat("Available Books", "856"));
        stats.add(stat("Total Members", "156"));
        stats.add(stat("Active Borrowings", "89"));
        stats.add(stat("Overdue Books", "12"));
        stats.add(stat("Today's Returns", "8"));

        p.add(stats, BorderLayout.CENTER);
        return p;
    }

    private JPanel stat(String t, String v) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel l1 = new JLabel(t, SwingConstants.CENTER);
        JLabel l2 = new JLabel(v, SwingConstants.CENTER);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 22));
        p.add(l1, BorderLayout.NORTH);
        p.add(l2, BorderLayout.CENTER);
        return p;
    }

    // ================= BOOKS =================
    private JPanel booksPanel() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // TABLE
        bookModel = new DefaultTableModel(
                new String[]{"ID","Title","Author","Genre","Year","Available"},0);
        JTable table = new JTable(bookModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // FORM
        JPanel form = new JPanel(new GridLayout(6,2,5,5));
        form.setBorder(BorderFactory.createTitledBorder("➕ Add New Book"));

        JTextField isbn = new JTextField();
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField genre = new JTextField();
        JTextField year = new JTextField();
        JTextField qty = new JTextField();

        form.add(new JLabel("ISBN:")); form.add(isbn);
        form.add(new JLabel("Title:")); form.add(title);
        form.add(new JLabel("Author:")); form.add(author);
        form.add(new JLabel("Genre:")); form.add(genre);
        form.add(new JLabel("Year:")); form.add(year);
        form.add(new JLabel("Quantity:")); form.add(qty);

        JButton save = new JButton("Save Book");
        save.addActionListener(e -> {
            if (title.getText().isEmpty() || author.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,"❌ Title & Author required");
                return;
            }

            bookModel.addRow(new Object[]{
                    bookIdCounter++,
                    title.getText(),
                    author.getText(),
                    genre.getText(),
                    year.getText(),
                    qty.getText()
            });

            JOptionPane.showMessageDialog(this,"✅ Book added successfully!");
            isbn.setText(""); title.setText(""); author.setText("");
            genre.setText(""); year.setText(""); qty.setText("");
        });

        JPanel bottom = new JPanel();
        bottom.add(save);
        bottom.add(new JButton("Cancel"));

        panel.add(form, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.NORTH);
        return panel;
    }

    // ================= MEMBERS =================
    private JPanel membersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // TABLE
        memberModel = new DefaultTableModel(
                new String[]{"ID","Name","Email","Phone","Status"},0);
        JTable table = new JTable(memberModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // FORM
        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        form.setBorder(BorderFactory.createTitledBorder("➕ Register New Member"));

        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField phone = new JTextField();
        JTextField address = new JTextField();

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Email:")); form.add(email);
        form.add(new JLabel("Phone:")); form.add(phone);
        form.add(new JLabel("Address:")); form.add(address);

        JButton register = new JButton("Register");
        register.addActionListener(e -> {
            if (name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,"❌ Name required");
                return;
            }

            memberModel.addRow(new Object[]{
                    "M00" + memberIdCounter++,
                    name.getText(),
                    email.getText(),
                    phone.getText(),
                    "Active"
            });

            JOptionPane.showMessageDialog(this,"✅ Member registered!");
            name.setText(""); email.setText("");
            phone.setText(""); address.setText("");
        });

        JPanel bottom = new JPanel();
        bottom.add(register);
        bottom.add(new JButton("Clear"));

        panel.add(form, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.NORTH);
        return panel;
    }

    // ================= REPORTS =================
    private JPanel reportsPanel() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JTextArea area = new JTextArea(
                "📈 LIBRARY ANALYTICS\n\n" +
                "• Clean Code – 45 borrowings\n" +
                "• Intro to Algorithms – 38 borrowings\n" +
                "• Effective Java – 32 borrowings"
        );
        area.setEditable(false);

        p.add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        btns.add(new JButton("Generate"));
        btns.add(new JButton("Export PDF"));
        btns.add(new JButton("Export Excel"));
        p.add(btns, BorderLayout.SOUTH);

        return p;
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new LibraryManagementSystem().setVisible(true));
    }
}
