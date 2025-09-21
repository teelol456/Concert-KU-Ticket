package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class SittingZone extends JFrame implements ActionListener{
    Container cp ;
    JLabel sittingzone ;
    JButton b1 , b2 ;
    public SittingZone(){
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
        sittingzone = new JLabel("Sitting Zone");
        sittingzone.setFont(new Font("Angsana New", Font.BOLD, 50));
        sittingzone.setBounds(350, 30, 400, 50);

        b1 = new JButton("OK");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(400,500,100,50);
        b1.setBackground(Color.decode("#FF9999"));

        b2 = new JButton("Back");
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBounds(30,30,100,50);
        b2.setBackground(Color.decode("#FF9999"));

        b1.addActionListener(this);
        b2.addActionListener(this);

        cp.add(sittingzone);
        cp.add(b1); cp.add(b2);
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
        if (e.getSource() == b1) {
            Popup("Thank you for your purchase!");
            this.dispose();
        }
        else if (e.getSource() == b2) {
            this.dispose();
            new Zone();
        }
    }

    public void Popup(String s) {
        JDialog d = new JDialog();
        JLabel l = new JLabel(s);
        l.setFont(new Font("",Font.PLAIN,18));
        l.setHorizontalAlignment(JLabel.CENTER);
        d.getContentPane();
        d.add(l);
        d.setSize(400,200); // กำหนดขนาด
        d.setLocationRelativeTo(null);
        //d.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        d.setVisible(true);
    }
    
}
