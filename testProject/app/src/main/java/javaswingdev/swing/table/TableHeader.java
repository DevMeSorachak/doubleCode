package javaswingdev.swing.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.RenderingHints;
import java.awt.GradientPaint;



public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(new Color(247, 176, 105));
        setFont(new Font("Kanit", 1, 17));
        setForeground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(grphcs);
        g2.setColor(new Color(230, 230, 230));
        g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//        GradientPaint gp = new GradientPaint(0, 0, new Color(186, 190, 255), getWidth(), getHeight(),new Color(186, 218, 255));
//        g2.setPaint(gp);
//        g2.fillRect(0, 0,getWidth() , getHeight());
        
        
    }
}
