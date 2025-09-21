package lib;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Concert extends JFrame implements ActionListener {
    Container cp ;
    JLabel concert ;
    JButton b1 , b2 , b3 , b4 , b5 , b6 ;
    public Concert() {
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
        concert = new JLabel("Concert KU Ticket");
        concert.setFont(new Font("Angsana New", Font.BOLD, 50));
        concert.setBounds(300, 30, 400, 50);

        b1 = new JButton("BUY TICKET");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(100,150,200,100);
        b1.setBackground(Color.decode("#FF9999"));

        cp.add(concert);
        cp.add(b1);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket");
        this.setSize(900,700);
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
