package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Concert.Concert;

public class StandingZoneGUI extends JFrame implements ActionListener{
    Concert concert;
    Container cp ;
    JLabel standzone , stand , stage , num , price , Boxlabel;
    JButton b1 , b2 , minusButton , plusbButton;

    public StandingZoneGUI(Concert concert){
        this.concert = concert;
        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }
    public StandingZoneGUI(){
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
        Draw1 a = new Draw1();
        a.setBounds(240, -100, 400, 400); 
        cp.add(a);

        if (concert != null) {
            try {
                Double standprice = concert.getStandPrice();
                JLabel lbstandprice = new JLabel(String.valueOf(standprice) + " / 1 Ticket");
                lbstandprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbstandprice.setBounds(450, 360, 300, 30);
                cp.add(lbstandprice);

                JLabel p = new JLabel("Standprice : ");
                p.setFont(new Font("Arial", Font.BOLD, 15));
                p.setForeground(Color.BLACK);
                p.setBounds(350, 360, 300, 30);
                cp.add(p);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    
        standzone = new JLabel("Stand Zone");
        standzone.setFont(new Font("Angsana New", Font.BOLD, 50));
        standzone.setBounds(370, 30, 400, 50);

        stage = new JLabel("Stage");
        stage.setBounds(400, 90, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));

        stand = new JLabel("StandZone");
        stand.setBounds(250, 200, 400, 150);
        stand.setFont(new Font("Arial", Font.BOLD, 30));
        stand.setForeground(Color.decode("#f5f5f5"));
        stand.setBackground(Color.decode("#A67B5B"));
        stand.setHorizontalAlignment(JLabel.CENTER);
        stand.setOpaque(true); // ทำให้พื้นหลังของ JLabel สามารถมองเห็นได้

        plusbButton = new JButton(" > ");
        plusbButton.setFont(new Font("Arial",Font.BOLD,20));
        plusbButton.setBounds(510,450,60,50);
        plusbButton.setForeground(Color.WHITE);
        plusbButton.setBackground(Color.decode("#414141"));

        minusButton = new JButton(" < ");
        minusButton.setFont(new Font("Arial",Font.BOLD,20));
        minusButton.setBounds(330,450,60,50);
        minusButton.setForeground(Color.WHITE);
        minusButton.setBackground(Color.decode("#414141"));

        num = new JLabel("0");
        num.setPreferredSize(new Dimension(200, 40));
        num.setFont(new Font("Times New Roman", Font.BOLD , 20));
        num.setHorizontalAlignment(JLabel.CENTER);
        num.setBounds(425, 455, 50, 40);
        num.setOpaque(true);
        num.setForeground(Color.WHITE);
        num.setBackground(Color.decode("#191919"));

        price = new JLabel("0");
        price.setPreferredSize(new Dimension(200, 40));
        price.setFont(new Font("Times New Roman", Font.BOLD , 20));
        price.setHorizontalAlignment(JLabel.CENTER);
        price.setBounds(375, 400, 150, 40);
        price.setOpaque(true);
        price.setForeground(Color.WHITE);
        price.setBackground(Color.decode("#191919"));

        b1 = new RoundedButton("Buy Tickets");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(378,550,150,50);
        b1.setForeground(Color.BLACK);
        
        Boxlabel = new RoundedLabel("", 20); 
        Boxlabel.setBounds(320, 400, 260, 210);
        Boxlabel.setBackground(Color.decode("#191919"));

        b2 = new RoundedButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBounds(30,30,100,50);
        b2.setBackground(Color.decode("#8C1007"));

        b1.addActionListener(this);
        b2.addActionListener(this);
        plusbButton.addActionListener(this);
        minusButton.addActionListener(this);

        cp.add(standzone);
        cp.add(stage);
        cp.add(stand);
        cp.add(plusbButton); cp.add(minusButton); cp.add(num); cp.add(price);
        cp.add(b1); cp.add(b2);
        cp.add(Boxlabel);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket");
        this.setSize(900,700);
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
    }

    int count = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (count == 0) {
                Popup("Must buy the ticket!!!");
            }
            else {
                Popup("Thank you for your purchase!");
                this.dispose();
            }
        }
        else if (e.getSource() == b2) {
            new ZoneGUI(concert); // ส่งข้อมูลเดิมกลับไป
            this.dispose(); // ปิดหน้าปัจจุบัน
        } 
        else if (e.getSource() == minusButton) {
            if (count > 0) {
                num.setText(--count + "");  // ลดค่าก่อนแสดง
                double total = concert.getStandPrice() * count;
                price.setText((int)(total) + " Bath");
            }
        } else if (e.getSource() == plusbButton) {
            if (count < 2) {
                num.setText(++count + "");  // เพิ่มค่าก่อนแสดง
                double total = concert.getStandPrice() * count;
                price.setText((int)(total) + " Bath");
            }
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

    class RoundedLabel extends JLabel {
        private int radius;

        public RoundedLabel(String text, int radius) {
            super(text);
            this.radius = radius;
            setOpaque(false); // ต้อง false เพื่อให้เราวาดเอง
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius); // วาดสี่เหลี่ยมมน
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new StandingZoneGUI();
    }
}


