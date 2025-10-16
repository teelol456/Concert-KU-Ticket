package AdminGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Booking.Booking;
import Booking.BookingManager;
import GUI.LoginGUI;
import GUI.*;

public class AdminConcert extends JFrame implements ActionListener {
    Container cp;
    JTable table;
    DefaultTableModel model;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3, b4, ChooseImgBtn;
    JLabel lbPreview;

    String selectedImageFilename = null;
    BookingManager bm = new BookingManager();

    public AdminConcert() {
        Initial();
        setComponent();
        Finally();
    }

    private void Initial() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(Color.decode("#CBDCEB"));
    }

    private void setComponent() {
        JLabel lbTitle = new JLabel("จัดการคอนเสิร์ต");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lbTitle.setBounds(350, 20, 300, 40);
        cp.add(lbTitle);

        String[] columns = {"ConcertName", "Day", "Location", "Stand", "Sit", "Image"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(100, 80, 700, 300);
        cp.add(scroll);

        JLabel lbName = new JLabel("ConcertName:");
        lbName.setBounds(100, 420, 100, 30);
        cp.add(lbName);
        t1 = new JTextField();
        t1.setBounds(220, 420, 200, 30);
        cp.add(t1);

        JLabel lbDay = new JLabel("Day (dd/mm/yyyy):");
        lbDay.setBounds(100, 460, 120, 30);
        cp.add(lbDay);
        t2 = new JTextField();
        t2.setBounds(220, 460, 200, 30);
        cp.add(t2);

        JLabel lbLocation = new JLabel("Location:");
        lbLocation.setBounds(100, 500, 100, 30);
        cp.add(lbLocation);
        t3 = new JTextField();
        t3.setBounds(220, 500, 200, 30);
        cp.add(t3);

        JLabel lbStandprice = new JLabel("Standing price:");
        lbStandprice.setBounds(100, 540, 100, 30);
        cp.add(lbStandprice);
        t4 = new JTextField();
        t4.setBounds(220, 540, 200, 30);
        cp.add(t4);

        JLabel lbSitprice = new JLabel("Sitting price:");
        lbSitprice.setBounds(100, 580, 100, 30);
        cp.add(lbSitprice);
        t5 = new JTextField();
        t5.setBounds(220, 580, 200, 30);
        cp.add(t5);

        JLabel lbImage = new JLabel("Image:");
        lbImage.setBounds(100, 620, 100, 30);
        cp.add(lbImage);

        // 🔹 Preview รูป
        lbPreview = new JLabel();
        lbPreview.setBounds(480, 430, 180, 200);
        lbPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cp.add(lbPreview);

        // 🔹 ปุ่มเลือกภาพ
        ChooseImgBtn = new JButton("Choose Image");
        ChooseImgBtn.setBounds(220, 620, 200, 30);
        ChooseImgBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("./img/"));
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String filename = selectedFile.getName();
                String destPath = "./img/" + filename;

                try {
                    new File("./img/").mkdirs();
                    java.nio.file.Files.copy(selectedFile.toPath(), new File(destPath).toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    selectedImageFilename = filename;

                    ImageIcon icon = new ImageIcon(destPath);
                    Image img = icon.getImage().getScaledInstance(lbPreview.getWidth(), lbPreview.getHeight(),
                            Image.SCALE_SMOOTH);
                    lbPreview.setIcon(new ImageIcon(img));

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage());
                }
            }
        });
        cp.add(ChooseImgBtn);

        // ปุ่ม Insert / Delete / Save / Back
        b1 = new JButton("Insert");
        b1.setBounds(700, 420, 150, 30);
        b1.addActionListener(this);
        cp.add(b1);

        b2 = new JButton("Delete");
        b2.setBounds(700, 460, 150, 30);
        b2.addActionListener(this);
        cp.add(b2);

        b4 = new JButton("Back to Login");
        b4.setBounds(700, 520, 150, 30);
        b4.addActionListener(this);
        cp.add(b4);

        b3 = new JButton("Save");
        b3.setBounds(700, 560, 150, 30);
        b3.addActionListener(this);
        cp.add(b3);

        loadFromCSV();
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket (Admin)");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Insert
            String name = t1.getText();
            String day = t2.getText();
            String location = t3.getText();
            String stand = t4.getText();
            String sit = t5.getText();

            if (!name.isEmpty() && !day.isEmpty() && !location.isEmpty() && !stand.isEmpty() && !sit.isEmpty()
                    && selectedImageFilename != null) {
                ImageIcon icon = new ImageIcon("./img/" + selectedImageFilename);
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
                icon.setDescription(selectedImageFilename);

                model.addRow(new Object[] { name, day, location, stand, sit, icon });

                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                lbPreview.setIcon(null);
                selectedImageFilename = null;
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields and select an image.");
            }

        } else if (e.getSource() == b2) { // Delete
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        } else if (e.getSource() == b3) { // Save
            saveToCSV();
        } else if (e.getSource() == b4) {
            this.dispose();
            new LoginGUI();
        }
    }

    private void saveToCSV() {
        try {
            new File("./File").mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter("./File/Concert.CSV"));

            List<String> concertNames = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                String name = model.getValueAt(i, 0).toString();
                String day = model.getValueAt(i, 1).toString();
                String location = model.getValueAt(i, 2).toString();
                String stand = model.getValueAt(i, 3).toString();
                String sit = model.getValueAt(i, 4).toString();
                String filename = ((ImageIcon) model.getValueAt(i, 5)).getDescription();

                bw.write(name + "," + day + "," + location + "," + stand + "," + sit + "," + filename);
                bw.newLine();

                concertNames.add(name);

                if (!bm.hasBooking(name)) {
                    bm.saveBooking(Booking.fromString(name + "," + 100 + "," + "[]"));
                }
            }

            bm.deleteNotIn(concertNames);
            bw.close();

            JOptionPane.showMessageDialog(this, "Saved to Concert.CSV successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving CSV: " + e.getMessage());
        }
    }

    private void loadFromCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./File/Concert.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length < 6) continue;

                String name = parts[0];
                String day = parts[1];
                String location = parts[2];
                String stand = parts[3];
                String sit = parts[4];
                String filename = parts[5];

                ImageIcon icon = new ImageIcon("./img/" + filename);
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
                icon.setDescription(filename);

                model.addRow(new Object[] { name, day, location, stand, sit, icon });
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e);
        }
    }

    public static void main(String[] args) {
        new AdminConcert();
    }
}
