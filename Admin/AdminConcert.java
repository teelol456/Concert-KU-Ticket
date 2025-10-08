package Admin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminConcert extends JFrame implements ActionListener{
    Container cp ;
    JTable table;
    DefaultTableModel model;
    JTextField t1, t2, t3, t4, t5;
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

        // ตาราง (ชื่อ, วัน, สถานที่, ราคาบัตรยืน , ราคาบัตรนั่ง ,รูปภาพ)
        String[] columns = {"ConcertName", "Day", "Location" ,"Stand","Sit", "Image"};
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

        JLabel lbStandprice = new JLabel("Standing ticket price:");
        lbStandprice.setBounds(100, 540, 100, 30);
        cp.add(lbStandprice);
        t4 = new JTextField();
        t4.setBounds(220, 540, 200, 30);
        cp.add(t4);

        JLabel lbSitprice = new JLabel("Sitting ticket price:");
        lbSitprice.setBounds(100, 580, 100, 30);
        cp.add(lbSitprice);
        t5 = new JTextField();
        t5.setBounds(220, 580, 200, 30);
        cp.add(t5);

        // ComboBox เลือกรูปจากโฟลเดอร์ ./img/
        JLabel lbImage = new JLabel("Image:");
        lbImage.setBounds(100, 620, 100, 30);
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
        cbImages.setBounds(220, 620, 200, 30);

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

        loadFromCSV();
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket (Admin)");
        this.setSize(900,700); // ขนาดหน้าต่าง
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String name = t1.getText();
            String day = t2.getText();
            String location = t3.getText();
            String stand = t4.getText();
            String sit = t5.getText();
            String filename = (String) cbImages.getSelectedItem();

            if (!name.isEmpty() && !day.isEmpty() && !location.isEmpty() && !stand.isEmpty() && !sit.isEmpty() && filename != null) {
                ImageIcon icon = new ImageIcon("./img/" + filename);    
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img); 
                icon.setDescription(filename); // เก็บชื่อไฟล์เพื่อ Save CSV

                model.addRow(new Object[]{name, day, location, stand, sit, icon});
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
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
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("./File/Concert.CSV");
            bw = new BufferedWriter(fw);

            for (int i = 0; i < model.getRowCount(); i++) {
                String name = model.getValueAt(i, 0).toString();
                String day = model.getValueAt(i, 1).toString();
                String location = model.getValueAt(i, 2).toString();
                String stand = model.getValueAt(i, 3).toString();
                String sit = model.getValueAt(i, 4).toString();
                
                String filename = ((ImageIcon) model.getValueAt(i, 5)).getDescription();
                bw.write(name + "," + day + "," + location + "," + stand + "," + sit + "," + filename);
                bw.newLine(); // ขึ้นบรรทัดใหม่
            }

            System.out.println("บันทึกข้อมูลลงไฟล์เรียบร้อยแล้ว");
            Popup("Saved to concerts.csv");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
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


    /*public static String[][] loadConcertsSimple() {
        String[][] data = new String[100][6]; 
        int row = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("./File/Concert.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6); // 6 คอลัมน์
                for (int i = 0; i < 6; i++) {
                    data[row][i] = parts[i];
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // คืนค่า array ที่มีข้อมูลจริง
        String[][] result = new String[row][6];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 6; j++) {
                result[i][j] = data[i][j];
            }
        }
        return result;
    }*/
    
    private void loadFromCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./File/Concert.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6); // ชื่อ, วัน, สถานที่,ราคาบัตรยืน , ราคาบัตรนั่ง , รูป
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

                model.addRow(new Object[]{name, day, location, stand , sit, icon});
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }  
    public static void main(String[] args) {
        new AdminConcert();
    }
}
