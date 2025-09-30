package lib;
//import Admin.*;
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
        b1.setFont(new Font("Arial",Font.BOLD,12));
        b1.setBounds(100,300,100,40);
        b1.setBackground(Color.decode("#FF9999"));
        
        b2 = new JButton("BUY TICKET");
        b2.setFont(new Font("Arial",Font.BOLD,12));
        b2.setBounds(400,300,100,40);
        b2.setBackground(Color.decode("#FF9999"));

        b3 = new JButton("BUY TICKET");
        b3.setFont(new Font("Arial",Font.BOLD,12));
        b3.setBounds(700,300,100,40);
        b3.setBackground(Color.decode("#FF9999"));

        b4 = new JButton("BUY TICKET");
        b4.setFont(new Font("Arial",Font.BOLD,12));
        b4.setBounds(100,550,100,40);
        b4.setBackground(Color.decode("#FF9999"));

        b5 = new JButton("BUY TICKET");
        b5.setFont(new Font("Arial",Font.BOLD,12));
        b5.setBounds(400,550,100,40);
        b5.setBackground(Color.decode("#FF9999"));

        b6 = new JButton("BUY TICKET");
        b6.setFont(new Font("Arial",Font.BOLD,12));
        b6.setBounds(700,550,100,40);
        b6.setBackground(Color.decode("#FF9999"));

        // โหลดรูปภาพจาก resources 
        // โดย หาจากgetclass() คือ class concert ที่เราอยู่ 
        //getResource() คือ path ของรูปที่อยู่ใน resources
        //หารูปที่มีขนาด ประมาณ 150x100 px หรือน้อยกว่านั้น หรือมากกว่านี้นิดนึง
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/Doro2.jpg")); 
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/HmDoro.jpg"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/img/HmDoro.jpg"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/img/HmDoro.jpg"));
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/img/HmDoro.jpg"));
        ImageIcon icon6 = new ImageIcon(getClass().getResource("/img/HmDoro.jpg"));

        // เพิ่มรูปบนแต่ละปุ่ม
        int gap = 10; // ระยะห่างระหว่างปุ่มกับรูปภาพ
        JLabel img1 = new JLabel(icon);
            img1.setBounds(100 + (100 - icon.getIconWidth())/2,
            300 - icon.getIconHeight() - gap,
            icon.getIconWidth(), icon.getIconHeight()); 

        JLabel img2 = new JLabel(icon2);
            img2.setBounds(400 + (100 - icon2.getIconWidth())/2, 
            300 - icon2.getIconHeight() - gap,
            icon2.getIconWidth(), icon2.getIconHeight());

        JLabel img3 = new JLabel(icon3);
            img3.setBounds(700 + (100 - icon3.getIconWidth())/2,
            300 - icon3.getIconHeight() - gap,
            icon3.getIconWidth(), icon3.getIconHeight());

        JLabel img4 = new JLabel(icon4);
            img4.setBounds(100 + (100 - icon4.getIconWidth())/2,
            550 - icon4.getIconHeight() - gap,
            icon4.getIconWidth(), icon4.getIconHeight());
         
        JLabel img5 = new JLabel(icon);
            img5.setBounds(400 + (100 - icon5.getIconWidth())/2,
            550 - icon5.getIconHeight() - gap,
            icon5.getIconWidth(), icon5.getIconHeight());

        JLabel img6 = new JLabel(icon6);
            img6.setBounds(700 + (100 - icon6.getIconWidth())/2,
            550 - icon6.getIconHeight() - gap,
            icon6.getIconWidth(), icon6.getIconHeight());

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        cp.add(concert);
        cp.add(b1); cp.add(b2); cp.add(b3); 
        cp.add(b4); cp.add(b5); cp.add(b6); 

        cp.add(img1); cp.add(img2); cp.add(img3);
        cp.add(img4); cp.add(img5); cp.add(img6);
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
            new Zone();
            this.dispose();
        } else if (e.getSource() == b2) {
            new Zone();
            this.dispose();
        } else if (e.getSource() == b3) {
            new Zone();
            this.dispose();
        } else if (e.getSource() == b4) {
            new Zone();
            this.dispose();
        } else if (e.getSource() == b5) {
            new Zone();
            this.dispose();
        } else if (e.getSource() == b6) {
            new Zone();
            this.dispose();
        }
    }
    
    
}

