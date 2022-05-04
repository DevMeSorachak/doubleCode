package javaswingdev.swing.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javaswingdev.swing.scroll.ScrollBar;
import javaswingdev.system.SystemColor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import javax.swing.Action;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;



public class Table extends JTable {
    private BufferedImage imageShadow;

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(60);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if(i1==4){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                if (selected) {
                    com.setForeground(new Color(52, 126, 215));
                    com.setBackground(new Color(224, 239, 244));
                    setFont(new Font("Kanit", Font.ITALIC, 18));
                    
                    
                } else {
                    com.setForeground(new Color(108, 145, 201));
                    com.setBackground(new Color(240, 230, 241));
                    setFont(new Font("Kanit", Font.PLAIN, 17));
                }
                return com;
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
        
    }
         
    
//    private void creadteShadow(){
//        int width = getWidth();
//        int height = getHeight();
//        int space = 12;
//        int margin = space/2;
//        imageShadow = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = imageShadow.createGraphics();
//        g2.fillRect(0,0, width-margin, height-margin);
//        g2.drawImage(new ShawdowRenderer, xform, this)
////        g2.drawImage(new ShadowRenderer(6,0.3f,new Color(160,160,160)).createShadow.(imageShadow),-4,-4,null);
//    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.setBorder(new EmptyBorder(5,10,5,10));
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(new Color(211, 113, 84));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
}
