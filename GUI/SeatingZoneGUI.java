package GUI;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import Booking.*;
import Concert.*;
import java.awt.*;
import Zone.*;

public class SeatingZoneGUI extends JFrame implements ActionListener , MouseListener{
    Concert concert;
    Container cp ;
    JLabel sittingzone , L , stage , c1 , c2 , c3 , red , white , black , price;
    JButton b1 , b2 ;
    JButton allBT[][] ;

    BookingManager bm = new BookingManager();
    Booking booking;


    public SeatingZoneGUI(Concert concert,Booking booking){
        this.concert = concert;
        this.booking = booking;

        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }

    public SeatingZoneGUI(){
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
        allBT = new JButton[5][16];
    }

    private void setComponent() {
        Draw1 a = new Draw1();
        a.setBounds(240, -100, 400, 400); 
        cp.add(a);

        stage = new JLabel("Stage");
        stage.setBounds(400, 90, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));

        c1 = new JLabel(); 
        c1.setBounds(650, 20, 30, 30);
        c1.setBackground(Color.red);
        c1.setHorizontalAlignment(JLabel.CENTER);
        c1.setOpaque(true);

        red = new JLabel("Selected");
        red.setBounds(690, 20, 100, 30);
        red.setFont(new Font("Arial", Font.PLAIN, 14));

        c2 = new JLabel();
        c2.setBounds(650, 70, 30, 30);
        c2.setBackground(Color.white);
        c2.setHorizontalAlignment(JLabel.CENTER);
        c2.setOpaque(true);

        white = new JLabel("Available");
        white.setBounds(690, 70, 100, 30);
        white.setFont(new Font("Arial", Font.PLAIN, 14));

        c3 = new JLabel();
        c3.setBounds(650, 120,30, 30);
        c3.setBackground(Color.black);
        c3.setHorizontalAlignment(JLabel.CENTER);
        c3.setOpaque(true);

        black = new JLabel("Unavailable");
        black.setBounds(690, 120, 100, 30);
        black.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,16 ,5,5)); //กำหนด layout เป็น grid 5x16 ช่อง โดยมีระยะห่างระหว่างปุ่ม 5px
        p.setBackground(Color.decode("#FFCCCC")); //กำหนดสีพื้นหลังของ panel

        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 16; j++) {
                allBT[i][j] = new JButton();
                allBT[i][j].setPreferredSize(new Dimension(20, 20));

                boolean isBooked = false;
                for (Seat s : booking.getSeatList()) {
                    if (s.getRow().equals(i) && s.getColumn().equals(j)) {
                        isBooked = true;
                        break; 
                    }
                }
                if (isBooked) {
                    allBT[i][j].setBackground(Color.BLACK);
                } else {
                    allBT[i][j].setActionCommand(i + "-" + j);
                    allBT[i][j].setBackground(Color.WHITE);
                    allBT[i][j].addMouseListener(this);
                }
                p.add(allBT[i][j]); 
            }
        }

        p.setBounds(100, 200, 700, 200); //กำหนดขนาดและตำแหน่งของ panel

        L = new JLabel("0");
        L.setPreferredSize(new Dimension(200, 40));
        L.setFont(new Font("Times New Roman", Font.BOLD , 20));
        L.setHorizontalAlignment(JLabel.CENTER);
        L.setBounds(425, 500, 50, 40);
        L.setOpaque(true);
        L.setBackground(Color.decode("#FF9999"));

        price = new JLabel("0");
        price.setPreferredSize(new Dimension(200, 40));
        price.setFont(new Font("Times New Roman", Font.BOLD , 20));
        price.setHorizontalAlignment(JLabel.CENTER);
        price.setBounds(375, 450, 150, 40);
        price.setOpaque(true);
        price.setBackground(Color.decode("#FF9999"));

        cp.add(c1); cp.add(c2); cp.add(c3); cp.add(red); cp.add(white); cp.add(black);
        cp.add(a);
        cp.add(stage);
        cp.add(price);
        cp.add(L);
        cp.add(p);
        

        sittingzone = new JLabel("Sitting Zone");
        sittingzone.setFont(new Font("Angsana New", Font.BOLD, 50));
        sittingzone.setBounds(350, 30, 400, 50);

        b1 = new JButton("OK");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(400,550,100,50);
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

    List<String> selecseatlist = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String joinedNames = String.join(";", selecseatlist);
            System.out.println("1");
            List<Seat> mergedSeats = booking.getSeatList();
            System.out.println("2");
            Booking nb = Booking.fromString(booking.getConcertName()+","+booking.getStandMaxTickets()+"," + "[" + joinedNames + "]"); 
            System.out.println("3");
            for (Seat s : nb.getSeatList()){
                mergedSeats.add(s);
            }
            System.out.println("4");
            bm.setBooking(new Booking(booking.getConcertName(), booking.getStandMaxTickets(), mergedSeats));

            Popup("Thank you for your purchase!");
            this.dispose();
        }
        else if (e.getSource() == b2) {
            new ZoneGUI(concert);
            this.dispose();
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
    
    int x = 0;
    Color tmp;
    @Override
    public void mouseClicked(MouseEvent e) { //เมื่อคลิกปุ่ม
        if (tmp == Color.white) {//ถ้าเป็นสีขาวให้เปลี่ยนเป็นเป็นสีแดง
            if ( x < 2 ) {e.getComponent().setBackground(Color.red);
                JButton b = (JButton)e.getSource();
                selecseatlist.add(b.getActionCommand());
            

                tmp = Color.red;
                L.setText(++x+"");//นับเลข+ชึ้นไปเมื่อกดเป็นสีแดง 
                double total = concert.getSeatPrice() * x ;
                price.setText((int)(total) + " Bath");
            }   
        } else if (tmp == Color.red) {
            JButton b = (JButton)e.getSource();
            System.out.println(b.getActionCommand());
            System.out.println(selecseatlist.indexOf(b.getActionCommand()));
            selecseatlist.remove(selecseatlist.indexOf(b.getActionCommand()));

            e.getComponent().setBackground(Color.white);//ถ้าเป็นสีแดงให้เป็นสีขาว
            tmp = Color.white;
            L.setText(--x+"");//ลบเลขเมื่อกดอีกครั้งเป็นสีขาว
            double total = concert.getSeatPrice() * x ;
            price.setText((int)(total) + " Bath");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { //เมื่อกดปุ่มเมาส์ลงไป
        
    }

    @Override
    public void mouseReleased(MouseEvent e) { //เมื่อปล่อยเมาส์ออกจากปุ่ม
        
    }

    @Override
    public void mouseEntered(MouseEvent e) { //เมื่อเอาเมาส์มาอยู่เหนือปุ่มให้เปลี่ยนสีปุ่ม
        tmp = e.getComponent().getBackground();
        e.getComponent().setBackground(Color.GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) { //เมื่อเอาเมาส์ออกจากปุ่มให้เปลี่ยนสีปุ่มกลับเป็นสีเดิม
        e.getComponent().setBackground(tmp);
    }
    
    class Draw1 extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.drawArc(10, 200, 400, 50, 0, 170);
        }
    }
    public static void main(String[] args) {
        new SeatingZoneGUI();
    }
}
