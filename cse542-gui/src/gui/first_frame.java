/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class first_frame extends javax.swing.JFrame {

	   
    private javax.swing.JButton addButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton unpauseButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private ArrayList<Student> students = new ArrayList<Student>() ;
   
    //constructor for student page
    public first_frame() {
        initComponents();
        jLabel1.setVisible(true);
        addDefaultRowToTables();
        removeButton.setEnabled(false);
        pauseButton.setEnabled(false);
        unpauseButton.setEnabled(false);
       
    }
    
    //method to populate student queue with 0-4 entries
    public void addDefaultRowToTables(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Student>templist = ListStudents();
        int noOfRows = ThreadLocalRandom.current().nextInt(0,5);
        Object rowData[] = new Object[3];
        
        if(noOfRows != 0){
           for(int i =1; i<=noOfRows; i++){
               
            rowData[0] = templist.get(i-1).userName;
            if (templist.get(i-1).paused == false){
                rowData[1] = "Unpaused";
            }
            Student s = new Student(templist.get(i-1).userName,templist.get(i-1).passWord,templist.get(i-1).email);
            students.add(s);
            model.addRow(rowData);
        } 
        }
        if(students.isEmpty()){
            jLabel1.setVisible(true);
        } else{
            jLabel1.setVisible(false);
        }
        
        
        
        
    }

    //remove functionality	
    private void removeActionPerformed(java.awt.event.ActionEvent evt) {
        JPasswordField password=new JPasswordField();
        JLabel label = new JLabel();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        int i = 0;
        while(i!=selectedRowIndex){
            i = i+1;
        }
        
        String username = jTable1.getValueAt(i, 0).toString();
        String email = students.get(selectedRowIndex).email;
        label.setText(email);
        
	Object[] message= {
            "Email of the student:", email,
            "Please enter your session password:", password};

        int value = JOptionPane.showConfirmDialog(null, message, "Enter session Password", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION){
            String p = new String(password.getPassword());
            students.remove(selectedRowIndex);
          
        }
        populateTable(students);
        jTable1.getSelectionModel().clearSelection();
        removeButton.setEnabled(false);
        pauseButton.setEnabled(false);
        unpauseButton.setEnabled(false);
        
        
    }

    //add functionality
    private void addActionPerformed(java.awt.event.ActionEvent evt) {

        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passwordField = new JTextField();
        JPasswordField password=new JPasswordField();
        
        Object[] addStudent = {
            "Name:", usernameField,
            "Email:", emailField,
            "Session Password:", password
        };
        JPanel panel = new JPanel();
        
        
        Object[] options = {"Add", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, addStudent, "Add a user", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if (result == JOptionPane.OK_OPTION){
            String addName = usernameField.getText();
            String addEmail = emailField.getText();
            String addPassword = new String(password.getPassword());
            boolean emptyName = false;
            boolean emptyEmail = false;
            String message = "Please enter a valid ";
            if (addName.isEmpty()){
                message+= "Username, ";
                emptyName = true;
            }
            if (addEmail.isEmpty()){
                emptyEmail = true;
                message += "Email, ";
            }

            if(emptyName  || emptyEmail ){
                message = message.substring(0,message.length()-2);
                     JOptionPane.showMessageDialog(null, message);
            
            } else{
                
                if(addName == null){
                    System.out.println("null");
                } else{
                    System.out.println("not empty");
                }
                Student s1 = new Student(addName,addPassword,addEmail);
                students.add(s1);
                populateTable(students);
                usernameField.setText("");
                emailField.setText("");
                password.setText("");
            }
        }
        jTable1.getSelectionModel().clearSelection();
        removeButton.setEnabled(false);
        pauseButton.setEnabled(false);
        unpauseButton.setEnabled(false);
    }


    //pause functionality
    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {
        
        JPasswordField password=new JPasswordField();
        JLabel label = new JLabel();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();

        String username = students.get(selectedRowIndex).toString();
        String email = students.get(selectedRowIndex).email.toString();
        
        
        label.setText(email);
        
	Object[] message= {
            "Email of the student:", email,
            "Please enter your session password:", password};
        
        int value = JOptionPane.showConfirmDialog(null, message, "Enter session Password", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION){
            String p = new String(password.getPassword());
            students.get(selectedRowIndex).paused = true;

        }
        
        populateTable(students);
        jTable1.getSelectionModel().clearSelection();
        pauseButton.setEnabled(false);
        removeButton.setEnabled(false);
        }

    //unpause functionality
    private void unpauseActionPerformed(java.awt.event.ActionEvent evt) {
        JPasswordField password=new JPasswordField();
        JLabel label = new JLabel();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        int i = 0;
        while(i!=selectedRowIndex){
            i = i+1;
        }
        
        String username = students.get(selectedRowIndex).toString();
        String email = students.get(selectedRowIndex).email.toString();
        label.setText(email);
        
	Object[] message= {
            "Email of the student:", email,
            "Please enter your session password:", password};
   
        int value = JOptionPane.showConfirmDialog(null, message, "Enter session Password", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION){
            String p = new String(password.getPassword());
            students.get(selectedRowIndex).paused = false;
        }
        
        populateTable(students);
        jTable1.getSelectionModel().clearSelection();
        unpauseButton.setEnabled(false);
        removeButton.setEnabled(false);
 
    }
    
    //table click listener
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        removeButton.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        String status = jTable1.getValueAt(selectedRowIndex, 1).toString();
        if (status.equals("Unpaused")){
            pauseButton.setEnabled(true);
        } else{
            unpauseButton.setEnabled(true);
        }
        
    }

    //method to populate the front end table at the end of an action
    public void populateTable(ArrayList<Student> slist){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for(int i =0; i<slist.size();i++){
            Object rowData[] = new Object[2];
            rowData[0] = slist.get(i).userName;
            //rowData[1] = slist.get(i).email;
            if(slist.get(i).paused == false){
                rowData[1] = "Unpaused";
                
            } else{
                rowData[1] = "Paused";
            }
            model.addRow(rowData);
            
        }
        if(slist.isEmpty()){
            jLabel1.setVisible(true);
        } else{
            jLabel1.setVisible(false);
        }
    }
    
    //prepopulated list
    public ArrayList ListStudents(){
        ArrayList<Student> slist  = new ArrayList<Student>();
        Student s1 = new Student("Tony Stark","pass123","tony@avengers.com");
        Student s2 = new Student("Bruce Banner","pass123","bruce@avengers.com");
        Student s3 = new Student("Steve Rogers","pass123","steve@avengers.com");
        Student s4 = new Student("Natasha Romanova","pass123","natasha@avengers.com");
        slist.add(s1);
        slist.add(s2);
        slist.add(s3);
        slist.add(s4);
        return slist;
    }

    

    @SuppressWarnings("unchecked")
    private void initComponents() {

        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pauseButton = new javax.swing.JButton();
        unpauseButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        unpauseButton.setText("Unpause");
        unpauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpauseActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        jLabel1.setText("Student queue is empty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unpauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pauseButton)
                                .addGap(52, 52, 52)
                                .addComponent(unpauseButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addGap(44, 44, 44)
                                .addComponent(removeButton)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }


}




