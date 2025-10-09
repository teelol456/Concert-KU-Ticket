package lib;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StandZone extends JFrame implements ActionListener{

    Container cp ;
    JLabel standzone , stand , stage ;
    JButton b1 , b2 ;
    public StandZone(){
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
        Draw1 a = new Draw1();
        a.setBounds(240, -100, 400, 400); 
        cp.add(a);

        standzone = new JLabel("Stand Zone");
        standzone.setFont(new Font("Angsana New", Font.BOLD, 50));
        standzone.setBounds(370, 30, 400, 50);

        stage = new JLabel("Stage");
        stage.setBounds(400, 90, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));

        stand = new JLabel("StandZone");
        stand.setBounds(250, 200, 400, 150);
        stand.setFont(new Font("Arial", Font.BOLD, 30));
        stand.setBackground(Color.decode("#E3EBFD"));
        stand.setHorizontalAlignment(JLabel.CENTER);
        stand.setOpaque(true); // ทำให้พื้นหลังของ JLabel สามารถมองเห็นได้

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

        cp.add(standzone);
        cp.add(stage);
        cp.add(stand);
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

    class Draw1 extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.drawArc(10, 200, 400, 50, 0, 170);
        }
    }
}
