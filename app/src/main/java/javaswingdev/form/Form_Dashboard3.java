package javaswingdev.form;
import FirebaseProject.FirebaseInitialize;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.main.MyPost;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;



public class Form_Dashboard3 extends javax.swing.JPanel {
    
     int INITIAL_X_SIZE = 100;
    ArrayList<ArrayList<ArrayList<String>>> listType = new ArrayList<ArrayList<ArrayList<String>>>(INITIAL_X_SIZE);
    ArrayList<ArrayList<String>> SuggestList = new ArrayList<ArrayList<String>>(INITIAL_X_SIZE);
    ArrayList<ArrayList<String>> MyPostList = new ArrayList<ArrayList<String>>(INITIAL_X_SIZE);
    
    FirebaseInitialize temp = new FirebaseInitialize();
    
    String username;
    MyPost newMyPost;

    public Form_Dashboard3(String username, MyPost getMyPost) {
        
        initComponents();
        this.username = username;
        this.newMyPost = getMyPost;
        init();
        
    }
    

    private void init() {
        table.fixTable(jScrollPane1);
        MyPost();
    }
    
    public void addPostOnMyPost() {
        try {
            ArrayList<String> tempString = new ArrayList<String>();
            System.out.println("123" + this.username);        
            tempString = temp.getAllPostFromUser(this.username);
            for (int i = 0; i < tempString.size(); i +=3) {
                ArrayList<String> tempString2 = new ArrayList<String>();
                tempString2.add(tempString.get(i));
                tempString2.add(tempString.get(i + 1));
                tempString2.add(tempString.get(i + 2));
                MyPostList.add(tempString2);
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(Form_Dashboard3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MyPost(){
        addPostOnMyPost();
        for (int i = 0; i < MyPostList.size(); i++) {
            table.addRow(new Object[]{MyPostList.get(i).get(0),MyPostList.get(i).get(1),MyPostList.get(i).get(2)});
        }
    }
    
        public void sentToDelete(String getUsername, String getUUIDStringFromClickDelete, String getTypeStringFromClickDelete) {
        try {
            
            temp.DeletePost(getUsername,getUUIDStringFromClickDelete, getTypeStringFromClickDelete);
        } catch (InterruptedException ex) {
            Logger.getLogger(Form_Dashboard3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sentDataToShow(String getUsername, String getUUIDStringFromClick, String getTypeStringFromClick){
        try {
            temp.presentPostOnMyPost(getUsername,getUUIDStringFromClick, getTypeStringFromClick, this.newMyPost);
        } catch (Exception ex) {
            Logger.getLogger(Form_Dashboard3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1000, 720));

        table.setBackground(new java.awt.Color(255, 204, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Type", "uuid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            table.getColumnModel().getColumn(1).setMinWidth(350);
            table.getColumnModel().getColumn(1).setPreferredWidth(350);
            table.getColumnModel().getColumn(1).setMaxWidth(350);
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Kanit", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(128, 72, 98));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-MY POST-");

        jLabel3.setFont(new java.awt.Font("Kanit", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(92, 151, 199));
        jLabel3.setText("Click on your Post");

        jLabel4.setFont(new java.awt.Font("Kanit", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(92, 151, 199));
        jLabel4.setText("to go to your post");

        jLabel1.setFont(new java.awt.Font("Kanit", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(92, 151, 199));
        jLabel1.setText("Post cannot be edited,");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Kanit", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(92, 151, 199));
        jLabel2.setText(" but can be delete");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addComponent(jLabel3))
                .addGap(283, 283, 283)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        
        int index = table.getSelectedRow();
        TableModel model = table.getModel();
        
        String StringTitle= model.getValueAt(index, 0).toString();
        String StringType = model.getValueAt(index, 1).toString();
        String StringUUID = model.getValueAt(index, 2).toString();
        
        String sentStringUsernameToMethodShow = this.username;
        String sentUUIDToMethodShow = StringUUID ;
        String sentTypeToMethodShow   = StringType;
        
        sentDataToShow(sentStringUsernameToMethodShow,sentUUIDToMethodShow, sentTypeToMethodShow);
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
