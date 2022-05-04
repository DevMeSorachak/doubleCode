package javaswingdev.form;

import FirebaseProject.FirebaseInitialize;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.TableModel;


//import javaswingdev.card.ModelCard;

public class Form_Dashboard2 extends javax.swing.JPanel {
    
    
    String username;
    
    int INITIAL_X_SIZE = 100;
    ArrayList<ArrayList<ArrayList<String>>> listType = new ArrayList<ArrayList<ArrayList<String>>>(INITIAL_X_SIZE);
    ArrayList<ArrayList<String>> SearchList = new ArrayList<ArrayList<String>>(INITIAL_X_SIZE);
    
    FirebaseInitialize temp = new FirebaseInitialize();
    
     String textKeyWord = "";
     String textType = "";
     int indexOfType = 0;
     String[] arrayType = {"Food","Sport","Game","Drama","Animal","Daily Life","Music","Study","Horror","Movie","Love","Funny","Religion","Travel","Fantasy","Cartoon"};

        public Form_Dashboard2(String username) {
            
            initComponents();
            this.username = username;
            init();
            
        }

        private void init() {
            table.fixTable(jScrollPane1);
            
        }
    
    
        public  void addPostOnType(String type)   {
            listType.add(temp.getAllPostOfType(type));        
        }   
    
        public void addAllPost() {
        addPostOnType("Food");
        addPostOnType("Sport");
        addPostOnType("Game");
        addPostOnType("Drama");
        addPostOnType("Animal");
        addPostOnType("Daily Life");
        addPostOnType("Music");
        addPostOnType("Study");
        addPostOnType("Horror");
        addPostOnType("Movie");
        addPostOnType("Love");
        addPostOnType("Funny");
        addPostOnType("Religion");
        addPostOnType("Travel");
        addPostOnType("Fantasy");
        addPostOnType("Cartoon");
        }
        
        public  void SearchPost(){

        if ("All".equalsIgnoreCase(textType)) {
            addAllPost();
            for (int i = 0; i < listType.size(); i++ ) {
                for (int j = 0; j < listType.get(i).size(); j ++) {
                   if (listType.get(i).get(j).get(1).toLowerCase().indexOf(textKeyWord) != -1) {
                        table.addRow(new Object[]{listType.get(i).get(j).get(1),listType.get(i).get(j).get(0),listType.get(i).get(j).get(3),listType.get(i).get(j).get(2)});
                    }
                
                }
        }
        } else {
                     indexOfType = java.util.Arrays.asList(arrayType).indexOf(textType);
                     System.out.println(indexOfType);
                     System.out.println(arrayType[indexOfType]);
                    addPostOnType(arrayType[indexOfType]);
                   
                  for (int i = 0; i < listType.get(0).size(); i ++) {                        
                            if (listType.get(0).get(i).get(1).toLowerCase().indexOf(textKeyWord) != -1 ) {
                                System.out.println(listType.get(0).get(i).get(1));
                                table.addRow(new Object[]{listType.get(0).get(i).get(1),listType.get(0).get(i).get(0),listType.get(0).get(i).get(3),listType.get(0).get(i).get(2)});
                            }
                  }
         }
         System.out.println("Search Done!");
    }
        
    public void sentDataToShow(String getUsername, String getUUIDStringFromClick, String getTypeStringFromClick){
        try {
            temp.presentPost(getUsername, getUUIDStringFromClick, getTypeStringFromClick);
        } catch (Exception ex) {
            Logger.getLogger(Form_Dashboard2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
   public  void getDataFromSearchPress(String getText, String getType) {
        textKeyWord = getText;
        textType = getType;
   }
        
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jLabel1 = new javax.swing.JLabel();

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

        jLabel1.setBackground(new java.awt.Color(218, 227, 255));
        jLabel1.setFont(new java.awt.Font("Kanit", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(96, 57, 109));
        jLabel1.setText("Search Result :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 123, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
