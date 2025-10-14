package GUI;

import javax.swing.*;
import java.awt.*;

public class CircleLabel extends JLabel {
    public CircleLabel() {
        setOpaque(false); // ไม่ให้พื้นหลังเป็นสี่เหลี่ยม
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดวงกลมเต็มพื้นที่ JLabel
        g2.setColor(getBackground());
        g2.fillOval(0, 0, getWidth(), getHeight());

        // วาดข้อความตรงกลาง (เหมือน JLabel ปกติ)
        super.paintComponent(g);
        g2.dispose();
    }
}
