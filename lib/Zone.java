package lib;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Zone extends JFrame implements ActionListener{
    Container cp ;
    JLabel zone , stage ;
    JButton b1 , b2 , b3 ;
    public Zone(){
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
        zone = new JLabel("Zone");
        zone.setFont(new Font("Angsana New", Font.BOLD, 50));
        zone.setBounds(400, 30, 400, 50);

        stage = new JLabel("Stage");
        stage.setBounds(550, 100, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));
        stage.setBackground(Color.decode("#FF9999"));
        stage.setHorizontalAlignment(JLabel.CENTER);
        stage.setOpaque(true);

        b1 = new JButton("Stand Zone");
        b1.setFont(new Font("Arial",Font.BOLD,30));
        b1.setBounds(500,250,300,150);
        b1.setBackground(Color.decode("#5b5d7aff"));
        b1.setHorizontalAlignment(JLabel.CENTER);
        
        b1.addActionListener(this);

        b2 = new JButton("Sit Zone");
        b2.setFont(new Font("Arial",Font.BOLD,30));
        b2.setBounds(500,450,300,150);
        b2.setBackground(Color.decode("#7dfc8eff"));
        b2.setHorizontalAlignment(JLabel.CENTER);

        b2.addActionListener(this);

        b3 = new JButton("Back");
        b3.setFont(new Font("Arial",Font.BOLD,20));
        b3.setBounds(30,30,100,50);
        b3.setBackground(Color.decode("#FF9999"));
        b3.addActionListener(this);

        cp.add(zone,BorderLayout.NORTH);
        cp.add(stage,BorderLayout.WEST);
        cp.add(b1,BorderLayout.WEST);
        cp.add(b2,BorderLayout.WEST);
        cp.add(b3);
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
        if (e.getSource() == b3) {
            this.dispose();
            new Concert();
        }
        else if (e.getSource() == b1) {
            this.dispose();
            new StandZone();
        }
        else if (e.getSource() == b2) {
            this.dispose();
            new SittingZone();
        }
    }
}
