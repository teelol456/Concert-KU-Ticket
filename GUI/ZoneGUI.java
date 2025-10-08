package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Concert.Concert;

public class ZoneGUI extends JFrame implements ActionListener{
    Container cp ;
    JLabel zone , stage , date , location , standprice , seatprice;
    JButton b1 , b2 , b3 ;
    Concert concert;

    public ZoneGUI(){
        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }

    public ZoneGUI(Concert concert){
        this.concert = concert;

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
        
        if (concert != null) {
            try {
                ImageIcon icon = new ImageIcon("./img/" + concert.getImage());
                JLabel lbPic = new JLabel(icon);
                lbPic.setBounds(150, 100, 200, 250); // ตำแหน่งรูป
                Image img = icon.getImage().getScaledInstance(lbPic.getWidth(), lbPic.getHeight(), Image.SCALE_SMOOTH);
                lbPic.setIcon(new ImageIcon(img));
                cp.add(lbPic);

                JLabel lbname = new JLabel(concert.getConcertName(), SwingConstants.CENTER);
                lbname.setFont(new Font("Arial", Font.BOLD, 20));
                lbname.setBounds(150, 100 + 250 + 20, 200, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
                cp.add(lbname);
                
                JLabel lbdate = new JLabel(concert.getDate());
                lbdate.setFont(new Font("Arial", Font.BOLD, 15));
                lbdate.setBounds(150, 415, 200, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
                cp.add(lbdate);

                JLabel lblocation = new JLabel(concert.getLocation());
                lblocation.setFont(new Font("Arial", Font.BOLD, 15));
                lblocation.setBounds(150, 465, 300, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
                cp.add(lblocation);

                Double standprice = concert.getStandPrice();
                JLabel lbstandprice = new JLabel(String.valueOf(standprice));
                lbstandprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbstandprice.setBounds(150, 515, 300, 30);
                cp.add(lbstandprice);

                Double seatprice = concert.getSeatPrice();
                JLabel lbseatprice = new JLabel(String.valueOf(seatprice));
                lbseatprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbseatprice.setBounds(150, 565, 300, 30);
                cp.add(lbseatprice);


            } catch (Exception e) {
                System.out.println(e);
            }
        }
        date = new JLabel("Date : ");
        date.setFont(new Font("Arial", Font.BOLD, 15));
        date.setBounds(100, 415, 300, 30);
        cp.add(date);

        location = new JLabel("Location : ");
        location.setFont(new Font("Arial", Font.BOLD, 15));
        location.setBounds(71, 465, 300, 30);
        cp.add(location);

        standprice = new JLabel("Standprice : ");
        standprice.setFont(new Font("Arial", Font.BOLD, 15));
        standprice.setBounds(56, 515, 300, 30);
        cp.add(standprice);

        seatprice = new JLabel("Seatprice : ");
        seatprice.setFont(new Font("Arial", Font.BOLD, 15));
        seatprice.setBounds(64, 565, 300, 30);
        cp.add(seatprice);

        stage = new JLabel("Stage");
        stage.setBounds(550, 100, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));
        stage.setBackground(Color.decode("#f5f5f5"));
        stage.setHorizontalAlignment(JLabel.CENTER);
        stage.setOpaque(true); // ทำให้พื้นหลังของ JLabel สามารถมองเห็นได้
        
        b1 = new JButton("Stand Zone");
        b1.setFont(new Font("Arial",Font.BOLD,30));
        b1.setBounds(500,250,300,150);
        b1.setBackground(Color.decode("#E3EBFD"));
        b1.setHorizontalAlignment(JLabel.CENTER);
        
        b1.addActionListener(this);

        b2 = new JButton("Sit Zone");
        b2.setFont(new Font("Arial",Font.BOLD,30));
        b2.setBounds(500,450,300,150);
        b2.setBackground(Color.decode("#F8D5F8"));
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
            new ConcertGUI();
        }
        else if (e.getSource() == b1) {
            this.dispose();
            new StandingZoneGUI();
        }
        else if (e.getSource() == b2) {
            this.dispose();
            new SeatingZoneGUI();
        }
    }
    public static void main(String[] args) {
        new ZoneGUI();
    }
}
