package Admin;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminConcert extends JFrame implements ActionListener{
    Container cp ;
    JTable table;
    DefaultTableModel model;
    JTextField t1, t2, t3;
    JButton b1, b2 , b3;
    JComboBox<String> cbImages;
    JLabel lbPreview;
    public AdminConcert(){
        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }   
    private void Initial() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane(); // สร้าง Container
        cp.setLayout(null); // ปิดการจัดการ Layout
        cp.setBackground(Color.decode("#FFCCCC")); // กำหนดสีพื้นหลัง
    }
    private void setComponent() {
        JLabel lbTitle = new JLabel("จัดการคอนเสิร์ต");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lbTitle.setBounds(350, 20, 300, 40);
        cp.add(lbTitle);

        // ตาราง (ชื่อ, วัน, สถานที่, รูปภาพ)
        String[] columns = {"ConcertName", "Day", "Location" , "Image"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(100, 80, 700, 300);
        cp.add(scroll);

        // ช่องกรอกข้อมูล
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

        // ComboBox เลือกรูปจากโฟลเดอร์ ./img/
        JLabel lbImage = new JLabel("Image:");
        lbImage.setBounds(100, 540, 100, 30);
        cp.add(lbImage);

        File folder = new File("./img/");

        // ดึงชื่อไฟล์ทั้งหมด
        String[] images = folder.list();

        // ตรวจสอบว่า folder มีไฟล์หรือไม่
        if (images == null) {
            images = new String[0]; // ถ้าไม่มีไฟล์ ให้ array ว่าง
        }

        // สร้าง JComboBox
        cbImages = new JComboBox<String>();

        // เพิ่มชื่อไฟล์ที่เป็น .png หรือ .jpg เข้า JComboBox
        for (int i = 0; i < images.length; i++) {
            if (images[i].contains(".png") || images[i].contains(".jpg")) {
                cbImages.addItem(images[i]);
            }
        }

        // กำหนดตำแหน่งและขนาด
        cbImages.setBounds(220, 540, 200, 30);

        // เพิ่ม JComboBox ลงใน Container
        cp.add(cbImages);

        // Preview รูป
        lbPreview = new JLabel();
        lbPreview.setBounds(480, 430, 180, 200);
        lbPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cp.add(lbPreview);

        cbImages.addActionListener(e -> {
            String filename = (String) cbImages.getSelectedItem();
            if (filename != null) {
                ImageIcon icon = new ImageIcon("./img/" + filename);
                Image img = icon.getImage().getScaledInstance(lbPreview.getWidth(), lbPreview.getHeight(), Image.SCALE_SMOOTH);
                lbPreview.setIcon(new ImageIcon(img));
            }
        });

        // ปุ่ม Add
        b1 = new JButton("Insert");
        b1.setBounds(700, 420, 150, 30);
        b1.addActionListener(this);
        cp.add(b1);

        // ปุ่ม Delete
        b2 = new JButton("Delete");
        b2.setBounds(700, 460, 150, 30);
        b2.addActionListener(this);
        cp.add(b2);

        // ปุ่ม Save
        b3 = new JButton("Save");
        b3.setBounds(700, 600, 150, 30);
        b3.addActionListener(this);
        cp.add(b3);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket");
        this.setSize(900,700); // ขนาดหน้าต่าง
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // กดปุ่ม Add → เพิ่มข้อมูลเข้า JTable
            String name = t1.getText().trim();
            String day = t2.getText().trim();
            String location = t3.getText().trim();
            String filename = (String) cbImages.getSelectedItem();

            if (!name.isEmpty() && !day.isEmpty() && !location.isEmpty() && filename != null) {
                ImageIcon icon = new ImageIcon("./img/" + filename);    
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
                icon.setDescription(filename); // เก็บชื่อไฟล์เพื่อ Save CSV

                model.addRow(new Object[]{name, day, location, icon});
                t1.setText("");
                t2.setText("");
                t3.setText("");
                lbPreview.setIcon(null);
            } else {
                Popup("Please fill in all fields.");
            }
        } else if (e.getSource() == b2) {
            // กดปุ่ม Delete → ลบข้อมูลที่เลือกใน JTable
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            } else {
                Popup("Please select a row to delete.");
            }
        } else if (e.getSource() == b3) {
            saveToCSV();
        }
    }

    private void saveToCSV() {
        try (PrintWriter pw = new PrintWriter("./File/Concert.csv")) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String name = (String) model.getValueAt(i, 0);
                String day = (String) model.getValueAt(i, 1);
                String location = (String) model.getValueAt(i, 2);
                ImageIcon icon = (ImageIcon) model.getValueAt(i, 3);
                String filename = icon != null ? icon.getDescription() : "";
                pw.println(name + "," + day + "," + location + "," + filename);
            }
            Popup("Saved to concerts.csv");
        } catch (Exception e) {
            System.out.println(e);
            Popup("Error saving CSV");
        }
    }
    private void Popup(String s) { // แสดง Popup
        JDialog d = new JDialog();
        JLabel l = new JLabel(s);
        l.setFont(new Font("",Font.PLAIN,18));
        d.getContentPane();
        d.add(l);
        d.pack();
        d.setLocationRelativeTo(null);
        //d.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        d.setVisible(true);
    }
}
