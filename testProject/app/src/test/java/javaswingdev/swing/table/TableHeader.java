package javaswingdev.swing.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.GradientPaint;
import java.awt.*;

public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.CYAN);
        setFont(new Font("sansserif", 1, 20));
        setForeground(new Color(102, 102, 102));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 =  (Graphics2D) grphcs;
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        GradientPaint gp = new GradientPaint(0, 0, new Color(186, 190, 255), getWidth(), getHeight(),new Color(186, 218, 255));
        g2.setPaint(gp);
        g2.fillRect(0, 0,getWidth() , getHeight());
    }
}
