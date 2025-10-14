package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Concert.Concert;

public class ZoneGUI extends JFrame implements ActionListener{
    Container cp ;
    JLabel zone , stage , Speaker , date , location , standprice , seatprice , seeleam ;
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
        cp.setBackground(Color.decode("#E9E3DF")); // กำหนดสีพื้นหลัง
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
                lbname.setBounds(150, 370, 200, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
                cp.add(lbname);
                
                JLabel lbdate = new JLabel(concert.getDate());
                lbdate.setFont(new Font("Arial", Font.BOLD, 15));
                lbdate.setBounds(150, 420, 200, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
             

                JLabel lblocation = new JLabel(concert.getLocation());
                lblocation.setFont(new Font("Arial", Font.BOLD, 15));
                lblocation.setBounds(150, 470, 300, 30); // ใช้ x ของรูป + กว้างรูปเท่ากับ lbname
              
                Double standprice = concert.getStandPrice();
                JLabel lbstandprice = new JLabel(String.valueOf(standprice));
                lbstandprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbstandprice.setBounds(150, 520, 300, 30);
              

                Double seatprice = concert.getSeatPrice();
                JLabel lbseatprice = new JLabel(String.valueOf(seatprice));
                lbseatprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbseatprice.setBounds(150, 570, 300, 30);

                cp.add(lbdate);
                cp.add(lblocation);
                cp.add(lbstandprice);
                cp.add(lbseatprice);

                lbdate.setForeground(Color.WHITE);
                lblocation.setForeground(Color.WHITE);  
                lbstandprice.setForeground(Color.WHITE);
                lbseatprice.setForeground(Color.WHITE);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        JLabel Seeleam = new JLabel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // สีพื้นหลังของ label
            g2.setColor(Color.decode("#191919"));  
            // วาดสี่เหลี่ยมขอบมน (ค่าหลังคือความโค้ง)
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            super.paintComponent(g);
            g2.dispose();
            }
        };
        
        Seeleam.setOpaque(false); // ต้องปิด opaque เพื่อให้เห็นพื้นหลังที่เราวาดเอง
        Seeleam.setFont(new Font("Arial", Font.BOLD, 16));
        Seeleam.setBounds(30, 410, 390, 220); // กำหนดตำแหน่งและขนาด

        date = new JLabel("Date : ");
        date.setFont(new Font("Arial", Font.BOLD, 15));
        date.setForeground(Color.WHITE);
        date.setBounds(100, 420, 300, 30);
        cp.add(date);

        location = new JLabel("Location : ");
        location.setFont(new Font("Arial", Font.BOLD, 15));
        location.setForeground(Color.WHITE);
        location.setBounds(71, 470, 300, 30);
        cp.add(location);

        standprice = new JLabel("Standprice : ");
        standprice.setFont(new Font("Arial", Font.BOLD, 15));
        standprice.setForeground(Color.WHITE);
        standprice.setBounds(56, 520, 300, 30);
        cp.add(standprice);

        seatprice = new JLabel("Seatprice : ");
        seatprice.setFont(new Font("Arial", Font.BOLD, 15));
        seatprice.setForeground(Color.WHITE);
        seatprice.setBounds(64, 570, 300, 30);
        cp.add(seatprice);

        stage = new JLabel("Stage");
        stage.setBounds(550, 100, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));
        stage.setBackground(Color.decode("#f5f5f5"));
        stage.setHorizontalAlignment(JLabel.CENTER);
        stage.setOpaque(true); // ทำให้พื้นหลังของ JLabel สามารถมองเห็นได้
        
        Speaker = new JLabel();
        Speaker.setBackground(Color.BLACK);
        Speaker.setBounds(500, 120, 300, 60); // ข้าง ๆ Stage
        Speaker.setHorizontalAlignment(JLabel.CENTER);
        Speaker.setOpaque(true); // ทำให้พื้นหลังของ JLabel สามารถมองเห็นได้

        b1 = new RoundedButton("Stand Zone (A)");
        b1.setFont(new Font("Arial",Font.BOLD,30));
        b1.setBounds(500,250,300,150);
        b1.setForeground(Color.decode("#f5f5f5"));
        b1.setBackground(Color.decode("#A67B5B"));
        b1.setHorizontalAlignment(JLabel.CENTER);
        
        b1.addActionListener(this);

        b2 = new RoundedButton("Sit Zone (B)");
        b2.setFont(new Font("Arial",Font.BOLD,30));
        b2.setBounds(500,450,300,150);
        b2.setForeground(Color.decode("#f5f5f5"));
        b2.setBackground(Color.decode("#6F4E37"));
        b2.setHorizontalAlignment(JLabel.CENTER);

        b2.addActionListener(this);

        b3 = new RoundedButton("Back");
        b3.setFont(new Font("Arial",Font.BOLD,20));
        b3.setForeground(Color.WHITE);
        b3.setBounds(30,30,100,50);
        b3.setBackground(Color.decode("#8C1007"));
        b3.addActionListener(this);

        cp.add(zone,BorderLayout.NORTH);
        cp.add(stage,BorderLayout.WEST); cp.add(Speaker,BorderLayout.WEST);
        cp.add(b1,BorderLayout.WEST);
        cp.add(b2,BorderLayout.WEST);
        cp.add(b3); cp.add(Seeleam);
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
            new StandingZoneGUI(concert);
        }
        else if (e.getSource() == b2) {
            this.dispose();
            new SeatingZoneGUI(concert);
        }
    }
    public static void main(String[] args) {
        new ZoneGUI();
    }
}
