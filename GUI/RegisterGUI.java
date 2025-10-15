package GUI;

import java.awt.event.*;
import javax.swing.*;

import UserAcount.Account;
import UserAcount.AccountManager;

import java.awt.*;

public class RegisterGUI extends JFrame implements ActionListener {
    Container cp ;
    JLabel register , username , password1 , password2;
    JTextField t1;
    JPasswordField t2 , t3;
    JButton b1 , b2 ;

    private AccountManager accountManager = new AccountManager();
    
    public RegisterGUI(){
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
        // เพิ่ม Component
        register = new JLabel("Register");
        username = new JLabel("Username");
        t1 = new JTextField();
        password1 = new JLabel("Password (Should be 8 - 16 characters)");
        t2 = new JPasswordField();
        password2 = new JLabel("Confirm Password");
        t3 = new JPasswordField();
        b1 = new RoundedButton("Submit");
        b2 = new RoundedButton("Cancel");

        // กำหนดขนาดและตำแหน่ง
        register.setBounds(140,20,150,40);
        register.setFont(new Font("Arial",Font.BOLD,30));
        // ช่องกรอก Username
        username.setBounds(50,90,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(45,120,300,30);
        // ช่องกรอก Password
        password1.setBounds(50,170,300,30);
        password1.setFont(new Font("Arial",Font.BOLD,15));
        t2.setBounds(45,200,300,30);
        // ช่องกรอก Re-enter Password
        password2.setBounds(50,250,200,30);
        password2.setFont(new Font("Arial",Font.BOLD,15));
        t3.setBounds(45,280,300,30);

        // ปุ่ม Submit
        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(210,350,140,40);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.decode("#C1856D"));

        // ปุ่ม Cancel
        b2.setFont(new Font("Arial",Font.BOLD,15));
        b2.setBounds(50,350,140,40);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.decode("#C1856D"));

        // เพิ่ม Event
        b1.addActionListener(this); //อย่าลืม
        b2.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(register); 
        cp.add(username); cp.add(t1);
        cp.add(password1); cp.add(t2);
        cp.add(password2); cp.add(t3);
        cp.add(b1); cp.add(b2);
    }
    private void Finally() {
        this.setTitle("Concert KU Ticket"); // = ชื่อ
        this.setSize(400,450); // = ขนาด
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // = ปิดโปรแกรม
        this.setVisible(true); // = แสดงผล
        this.setLocationRelativeTo(null);  // = แสดงผลตรงกลาง
        this.setResizable(false); // = ขยายขนาดไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) { // ตรวจสอบว่าปุ่มไหนถูกกด
        if (e.getSource() == b1) { // ถ้ากดปุ่ม Submit
            String pass1 = new String(t2.getPassword()); // แปลง JPasswordField เป็น String
            String pass2 = new String(t3.getPassword()); // แปลง JPasswordField เป็น String
            createAccount(t1.getText(),pass1,pass2);
        }
        else if (e.getSource() == b2) { // ถ้ากดปุ่ม Cancel
            this.dispose(); // Close the register window
            new LoginGUI(); // Open the login window
        }
    }

    private void createAccount(String username, String password1,String password2) {
        if(!accountManager.hasAccount(username)) {
            if(!(accountManager.PasswordSecure(password1).equals(""))){
                Popup(accountManager.PasswordSecure(password1));
            }
            else if(password1.equals(password2)){
                String s = username + "," +password1;
                Account a = Account.fromString(s);     
                accountManager.SaveAccount(a);
                this.dispose(); 
                new LoginGUI(); 
                Popup("Complete!!!");
            }
            else Popup("Passwords do not match.");
        }
        else Popup("The username is already in use.");
    }
    public void Popup(String s) { // แสดง Popup
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
}