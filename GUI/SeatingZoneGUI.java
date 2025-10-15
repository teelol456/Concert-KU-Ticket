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
    JLabel sittingzone , L , stage , c1 , c2 , c3 , red , white , black , price , Boxlabel ,  Textprice , TextTicket , A , B , C , D , E;
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

    private void Initial() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane(); // สร้าง Container
        cp.setLayout(null); // ปิดการจัดการ Layout
        cp.setBackground(Color.decode("#E9E3DF")); // กำหนดสีพื้นหลัง
        allBT = new JButton[5][16];
    }

    private void setComponent() {
        Draw1 a = new Draw1();
        a.setBounds(240, -100, 400, 400); 
        cp.add(a);

        if (concert != null) {
            try {
                Double seatprice = concert.getSeatPrice();
                JLabel lbseatprice = new JLabel(String.valueOf(seatprice) + " / 1 Ticket");
                lbseatprice.setFont(new Font("Arial", Font.BOLD, 15));
                lbseatprice.setBounds(450, 410, 300, 30);
                cp.add(lbseatprice);

                JLabel seat = new JLabel("Seatprice :");
                seat.setFont(new Font("Arial", Font.BOLD, 15));
                seat.setForeground(Color.BLACK);
                seat.setBounds(350, 410, 300, 30);
                cp.add(seat);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        stage = new JLabel("Stage");
        stage.setBounds(400, 90, 200, 100);
        stage.setFont(new Font("Arial", Font.BOLD, 30));

        c1 = new JLabel();
        c1.setBounds(690, 20, 30, 30);
        c1.setBackground(Color.red);
        c1.setHorizontalAlignment(JLabel.CENTER);
        c1.setOpaque(true);

        red = new JLabel("Selected");
        red.setBounds(730, 20, 100, 30);
        red.setFont(new Font("Arial", Font.PLAIN, 14));

        c2 = new JLabel();
        c2.setBounds(690, 70, 30, 30);
        c2.setBackground(Color.white);
        c2.setHorizontalAlignment(JLabel.CENTER);
        c2.setOpaque(true);

        white = new JLabel("Available");
        white.setBounds(730, 70, 100, 30);
        white.setFont(new Font("Arial", Font.PLAIN, 14));

        c3 = new JLabel();
        c3.setBounds(690, 120,30, 30);
        c3.setBackground(Color.black);
        c3.setHorizontalAlignment(JLabel.CENTER);
        c3.setOpaque(true);

        black = new JLabel("Unavailable");
        black.setBounds(730, 120, 100, 30);
        black.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,16 ,5,5)); //กำหนด layout เป็น grid 5x16 ช่อง โดยมีระยะห่างระหว่างปุ่ม 5px
        p.setBackground(Color.decode("#E9E3DF")); //กำหนดสีพื้นหลังของ panel

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

        Textprice = new JLabel("Total Price");
        Textprice.setFont(new Font("Arial", Font.BOLD, 15));
        Textprice.setForeground(Color.WHITE);
        Textprice.setBounds(350, 464, 300, 30);
       

        TextTicket = new JLabel("Tickets");
        TextTicket.setFont(new Font("Arial", Font.BOLD, 15));   
        TextTicket.setForeground(Color.WHITE);
        TextTicket.setBounds(350, 524, 300, 30);


        price = new JLabel("0"); // ราคาทั้งหมด 
        price.setForeground(Color.WHITE);
        price.setPreferredSize(new Dimension(200, 40));
        price.setFont(new Font("Times New Roman", Font.BOLD , 20));
        price.setHorizontalAlignment(JLabel.CENTER);
        price.setBounds(450, 460, 120, 40);
        price.setOpaque(true);
        price.setBackground(Color.decode("#191919"));

        L = new JLabel("0"); // จำนวนตั๋ว
        L.setForeground(Color.WHITE);
        L.setPreferredSize(new Dimension(200, 40));
        L.setFont(new Font("Times New Roman", Font.BOLD , 20));
        L.setHorizontalAlignment(JLabel.CENTER);
        L.setBounds(490, 520, 50, 40);
        L.setBackground(Color.decode("#191919"));
        L.setOpaque(true);

        b1 = new RoundedButton("Buy Tickets");
        b1.setForeground(Color.black);
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(380,580,150,50);
    
        Boxlabel = new RoundedLabel("", 20); 
        Boxlabel.setBounds(330, 450, 250, 200);
        Boxlabel.setBackground(Color.decode("#191919"));

        cp.add(c1); cp.add(c2); cp.add(c3); cp.add(red); cp.add(white); cp.add(black);
        cp.add(a);
        cp.add(Textprice);
        cp.add(TextTicket);
        cp.add(stage);
        cp.add(price);
        cp.add(L);
        cp.add(p);
        
        sittingzone = new JLabel("Sitting Zone");
        sittingzone.setFont(new Font("Angsana New", Font.BOLD, 50));
        sittingzone.setForeground(Color.BLACK);
        sittingzone.setBounds(350, 30, 400, 50);

        b2 = new RoundedButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBounds(30,30,100,50);
        b2.setBackground(Color.decode("#8C1007"));

        b1.addActionListener(this);
        b2.addActionListener(this);

        cp.add(sittingzone);
        cp.add(b1); cp.add(b2);
        cp.add(Boxlabel);

        String[] rowLabels = {"A", "B", "C", "D", "E"};
        for (int i = 0; i < 5; i++) {
            JLabel rowLabel = new JLabel(rowLabels[i]);
            rowLabel.setFont(new Font("Arial", Font.BOLD, 16));
            rowLabel.setBounds(120, 190 + i * 50, 30, 30); // จัดตำแหน่งแนวตั้งให้ตรงกับแต่ละแถว
            cp.add(rowLabel);
        }

        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 1; i <= 16; i++) {
            JLabel num = new JLabel(i + " ");
            num.setFont(new Font("Arial", Font.PLAIN, 16));
            row.add(num);
            row.add(Box.createRigidArea(new Dimension(10, 0))); // ช่องว่างระหว่างเลข
        }
        add(row);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket");
        this.setSize(900,700);
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
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

    List<String> selecseatlist = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (x == 0) {
                Popup("Must buy the ticket!!!");
            } else{
                String joinedNames = String.join(";", selecseatlist);
                List<Seat> mergedSeats = booking.getSeatList();
                Booking nb = Booking.fromString(booking.getConcertName()+","+booking.getStandMaxTickets()+"," + "[" + joinedNames + "]"); 
                for (Seat s : nb.getSeatList()){
                    mergedSeats.add(s);
                }
                bm.setBooking(new Booking(booking.getConcertName(), booking.getStandMaxTickets(), mergedSeats));

                Popup("Thank you for your purchase!");
                this.dispose();
            }
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
}
