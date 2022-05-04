package javaswingdev.form;

import FirebaseProject.FirebaseInitialize;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;




public class Form_Dashboard extends javax.swing.JPanel  {
        
    int INITIAL_X_SIZE = 100;
    ArrayList<ArrayList<ArrayList<String>>> listType = new ArrayList<ArrayList<ArrayList<String>>>(INITIAL_X_SIZE);
    String username;
    
    FirebaseInitialize temp = new FirebaseInitialize();
    int indexOfType = 0;
    String[] arrayType = {"Food","Sport","Game","Drama","Animal","Daily Life","Music","Study","Horror","Movie","Love","Funny","Religion","Travel","Fantasy","Cartoon"};
    String[] arrayTypeTest = {"Food","Sport","Game","Drama","Animal","Daily Life","Music","Study","Horror","Movie","Love","Funny","Religion","Travel","Fantasy","Cartoon"};
    
    public Form_Dashboard(String username) {
         
        initComponents();
        this.username = username;
        init();
               
    }

    private void init() {
        table.fixTable(jScrollPane1);
        suggestPost();
    }

    
    public void addAllPost() {
        Random rand = new Random();
        ArrayList<Integer> randomOnNumber= new ArrayList<Integer>();
        int c = 0;
        while (c < 9) {
            int a = rand.nextInt(16);
            if (randomOnNumber.indexOf(a) == -1) {
                randomOnNumber.add(a);
                takeRandomOnType(arrayTypeTest[a]);
                c++;
            }
            
        }
    }
    
    
    public void suggestPost()   {
        addAllPost();
        shuffleSuggestionPost();
    }
    
    public void shuffleSuggestionPost(){
       
       for (int i = 0; i < listType.size(); i++) {
           for (int j = 0 ; j < listType.get(i).size(); j++) {
               
                   // title type like uuid
                   table.addRow(new Object[]{listType.get(i).get(j).get(1),listType.get(i).get(j).get(0),listType.get(i).get(j).get(3),listType.get(i).get(j).get(2)});
              
           }
        }

    }
    
    
    
    
    public void takeRandomOnType(String type) {
        listType.add(temp.getRandomPostOfType(type));    
    }
    
    public void sentDataToShow(String getUsername, String getUUIDStringFromClick, String getTypeStringFromClick){
        try {
            temp.presentPost(getUsername,getUUIDStringFromClick, getTypeStringFromClick);
        } catch (Exception ex) {
            Logger.getLogger(Form_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        table.setBackground(new java.awt.Color(255, 204, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Type", "Like", "uuid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(1).setMinWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setMaxWidth(200);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(0);
            table.getColumnModel().getColumn(3).setPreferredWidth(0);
            table.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
         int index = table.getSelectedRow();
        TableModel model = table.getModel();
        
         
        
        String StringTitle= model.getValueAt(index, 0).toString();
        String StringType = model.getValueAt(index, 1).toString();
        String StringLikes = model.getValueAt(index, 2).toString();
        String StringUUID = model.getValueAt(index, 3).toString();
        
        
        String sentStringUsernameToMethodShow = this.username;
        String sentUUIDToMethodShow = StringUUID ;
        String sentTypeToMethodShow   = StringType;
        
        
        sentDataToShow(sentStringUsernameToMethodShow,sentUUIDToMethodShow, sentTypeToMethodShow);
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
