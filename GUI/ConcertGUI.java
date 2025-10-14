package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Concert.*;

import java.util.*;
import java.util.List;

public class ConcertGUI extends JFrame implements ActionListener {
    Container cp;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();

    private ConcertManager concertManager = new ConcertManager();
    
    public ConcertGUI() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(Color.decode("#FFCCCC"));

        JLabel concert = new JLabel("Concert KU Ticket");
        concert.setFont(new Font("Angsana New", Font.BOLD, 50));
        concert.setBounds(300, 30, 400, 50);
        cp.add(concert);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#FFCCCC"));

        loadConcertsFromCSV(panel); // โหลด CSV และสร้างปุ่ม

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 100, 800, 550); //
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        cp.add(scrollPane);
        

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
            JButton btn = new JButton(c.getConcertName());
            btn.setBounds(x, y, 150, 195);
            btn.setBackground(Color.decode("#FF9999"));
            btn.addActionListener(this);

            // โหลดรูป
            ImageIcon icon = new ImageIcon("./img/" + c.getImage());
            Image img = icon.getImage().getScaledInstance(150, 173, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);

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
        JButton b = (JButton) e.getSource();
        int index = buttons.indexOf(b);
        new ZoneGUI(concertManager.getConcert(index));
        this.dispose();
    }

    public static void main(String[] args) {
        new ConcertGUI();
    }
}
