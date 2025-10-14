package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Admin.AdminLogin;
import UserAcount.Account;
import UserAcount.AccountManager;


public class LoginGUI extends JFrame implements ActionListener{
    Container cp ;
    JLabel login , username , password , lRegister , admin;
    JTextField t1;
    JPasswordField t2;
    JButton b1 ;

    private AccountManager accountManager = new AccountManager();
    
    public LoginGUI(){
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
        // เพิ่ม Component
        login = new JLabel("Login");
        username = new JLabel("Username");
        t1 = new JTextField();
        password = new JLabel("Password");
        t2 = new JPasswordField();
        b1 = new JButton("Submit");

        // กำหนดขนาดและตำแหน่ง
        login.setBounds(150,20,100,40);
        login.setFont(new Font("Arial",Font.BOLD,30));

        username.setBounds(50,100,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(45,130,300,30);

        password.setBounds(50,170,100,30);
        password.setFont(new Font("Arial",Font.BOLD,15));
        t2.setBounds(45,200,300,30);

        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(130,290,140,40);
        b1.setBackground(Color.decode("#FF9999"));

        lRegister = new JLabel("<html><u>Register here</u></html>"); // ใช้ HTML เพื่อขีดเส้นใต้ข้อความ
        lRegister.setFont(new Font("Arial", Font.PLAIN, 14));
        lRegister.setForeground(Color.BLUE); // เปลี่ยนสีข้อความ
        lRegister.setBounds(255,240,120,30);
        lRegister.setCursor(new Cursor(Cursor.HAND_CURSOR)); // เปลี่ยนเคอร์เซอร์เมื่อชี้ไปที่ป้าย
        lRegister.addMouseListener(new MouseAdapter() { // เพิ่ม MouseListener
            @Override
            public void mouseClicked(MouseEvent e) { // เมื่อคลิกที่ป้าย Register
                Register(); // เรียกฟังก์ชัน Register()
            }
        });
        admin = new JLabel("<html><u>Admin</u></html>"); // ใช้ HTML เพื่อขีดเส้นใต้ข้อความ
        admin.setFont(new Font("Arial", Font.PLAIN, 14));
        admin.setForeground(Color.BLUE); // เปลี่ยนสีข้อความ
        admin.setBounds(320,10,120,30);
        admin.setCursor(new Cursor(Cursor.HAND_CURSOR)); // เปลี่ยนเคอร์เซอร์เมื่อชี้ไปที่ป้าย
        admin.addMouseListener(new MouseAdapter() { // เพิ่ม MouseListener
            @Override
            public void mouseClicked(MouseEvent e) { // เมื่อคลิกที่ป้าย Register
                Admin(); // เรียกฟังก์ชัน Register()
            }
        });
        // เพิ่ม Event
        b1.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(login); 
        cp.add(username); cp.add(t1);
        cp.add(password); cp.add(t2);
        cp.add(b1); cp.add(lRegister); cp.add(admin);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket"); // = ชื่อ
        this.setSize(400,400); // = ขนาด
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // = ปิดโปรแกรม
        this.setVisible(true); // = แสดงผล
        this.setLocationRelativeTo(null);  // = แสดงผลตรงกลาง
        this.setResizable(false); // = ขยายขนาดไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)) { // ตรวจสอบว่ากดปุ่ม Submit หรือไม่
            CheckAccount(t1.getText() , new String(t2.getPassword()));
        }
    }

    private void CheckAccount(String username, String password) {
        Account acc = accountManager.getAccount(username);
        if(accountManager.hasAccount(username)){
            if(acc.getPassword().equals(password)){
                new ConcertGUI(); 
                this.dispose(); 
            } else Popup("Invalid username or password.");
        }   
        else Popup("Invalid username or password.");
    }

    private void Popup(String s) { // แสดง Popup
        JDialog d = new JDialog();
        JLabel l = new JLabel(s);
        l.setFont(new Font("",Font.PLAIN,18));
        d.getContentPane();
        d.add(l);
        d.pack();
        d.setLocationRelativeTo(null);
        //d.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        d.setVisible(true);
    }
    public void Register() {
        new RegisterGUI(); // เปิดหน้าต่าง Register
        this.dispose(); // ปิดหน้าต่าง Login
    }
    public void Admin(){
        new AdminLogin();
        this.dispose();
    }
}
