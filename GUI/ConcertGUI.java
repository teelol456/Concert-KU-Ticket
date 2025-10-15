package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Concert.*;

import java.util.*;
import java.util.List;

public class ConcertGUI extends JFrame implements ActionListener {
    Container cp;
    JButton b3 ;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();

    private ConcertManager concertManager = new ConcertManager();
    
    public ConcertGUI() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(Color.decode("#E9E3DF"));

        JLabel concert = new JLabel("Concert KU Ticket");
        concert.setFont(new Font("Angsana New", Font.BOLD, 50));
        concert.setBounds(300, 30, 400, 50);
        cp.add(concert);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE); // สีพื้นหลังข้างใน

        loadConcertsFromCSV(panel); // โหลด CSV และสร้างปุ่ม

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 100, 800, 550); //
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        cp.add(scrollPane);

        b3 = new RoundedButton("Logout");
        b3.setFont(new Font("Arial",Font.BOLD,20));
        b3.setBounds(30,30,100,50);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.decode("#8C1007"));
        b3.addActionListener(this);
        cp.add(b3);
        

        this.setTitle("Concert KU Ticket");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void loadConcertsFromCSV(JPanel panel) {
        
        List<Concert> cl = concertManager.getConcertList();
        int x = 50, y = 50; // ปรับช่องว่าง ด้านบน ด้านข้าง
        int count = 0;
        for (Concert c : cl){
        
            ImageIcon icon = new ImageIcon("./img/" + c.getImage());
            Image img = icon.getImage().getScaledInstance(150, 173, Image.SCALE_SMOOTH);

            // ปุ่ม custom ที่มีขอบมน
            JButton btn = new JButton(c.getConcertName()) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int arc = 20; //  ปรับค่าความโค้ง (ยิ่งมากยิ่งมน)
                    Shape round = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);

                    // วาดพื้นหลังขอบมน
                    g2.setColor(getBackground());
                    g2.fill(round);

                    // ครอบขอบมนก่อนวาดเนื้อหาในปุ่ม
                    g2.setClip(round);
                    super.paintComponent(g2);
                    g2.dispose();
                }
            };

            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.setIcon(new ImageIcon(img));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setBounds(x, y, 150, 195);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.addActionListener(this);

            panel.add(btn);
            buttons.add(btn);

            x += 270;
            count++;
            if (count % 3 == 0) {
                x = 50;
                y += 250;
            }
            panel.setPreferredSize(new Dimension(750, y + 250));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b3) {
            this.dispose();
            new LoginGUI();
        }
        JButton b = (JButton) e.getSource();
        int index = buttons.indexOf(b);
        new ZoneGUI(concertManager.getConcert(index));
        this.dispose();
    }

    public static void main(String[] args) {
        new ConcertGUI();
    }
}