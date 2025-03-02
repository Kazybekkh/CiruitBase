package com.kazcables.recordview;

import com.kazcables.forms.Home;
import com.kazcables.forms.addClient;
import com.kazcables.model.Branch;
import com.kazcables.model.Client;
import com.kazcables.model.Organization;
import com.kazcables.util.Db;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ClientRecords extends javax.swing.JPanel {

    private final Organization organization;
    private final Home home;
    private final Stack<String> added;
    private final Stack<String> removed;

    public ClientRecords(Organization org, Home home) {
        initComponents();

        this.added = new Stack<>();
        this.removed = new Stack<>();
        this.organization = org;
        this.home = home;
        for (Branch b : this.organization.getBranches().values()) {
            b.initClients();
        }

        String[] searchParameter = new String[]{"by client_name", "by client_id"};
        DefaultComboBoxModel<String> jcbmodel = new DefaultComboBoxModel<>(searchParameter);
        jcb_search_parameter.setModel(jcbmodel);
        if (!jcb_search_parameter.isEnabled() || !jcb_search_parameter.isVisible()) {
            jcb_search_parameter.setEnabled(true);
            jcb_search_parameter.setVisible(true);
        }
        showClients();
    }

    public Stack<String> getAdded() {
        return added;
    }

    public Stack<String> getRemoved() {
        return removed;
    }
    public Organization getOrganization() {
        return organization;
    }

    public Home getHome() {
        return home;
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JTable();
        lbl_clientRecords = new javax.swing.JLabel();
        btn_addClient = new javax.swing.JButton();
        jcb_search_parameter = new javax.swing.JComboBox<>();
        tf_searchField = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_save_exit = new javax.swing.JButton();
        btn_removeClient = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 520));

        clientList.setAutoCreateRowSorter(true);
        clientList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "client_id", "client_name", "branch_id", "phone", "email", "assigned_to"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(clientList);

        lbl_clientRecords.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lbl_clientRecords.setText("Client Records");

        btn_addClient.setText("Add Client");
        btn_addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClientActionPerformed(evt);
            }
        });

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_save_exit.setText("Save Changes ");
        btn_save_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_exitActionPerformed(evt);
            }
        });

        btn_removeClient.setText("Remove Client");
        btn_removeClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btn_addClient)
                        .addGap(46, 46, 46)
                        .addComponent(btn_removeClient)
                        .addGap(31, 31, 31)
                        .addComponent(btn_save_exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lbl_clientRecords)))
                .addContainerGap(428, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcb_search_parameter, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_search_parameter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addGap(4, 4, 4)
                .addComponent(lbl_clientRecords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addClient)
                    .addComponent(btn_save_exit)
                    .addComponent(btn_removeClient))
                .addGap(78, 78, 78))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String val = (String) tf_searchField.getText();
        if (val.isEmpty()){JOptionPane.showMessageDialog(null, "search field cannot be empty!", "search error", 1);}
        performSearch(val,jcb_search_parameter);
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClientActionPerformed
        openAddClientDialog();
    }//GEN-LAST:event_btn_addClientActionPerformed

    private void btn_save_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_exitActionPerformed
        saveExit();
    }//GEN-LAST:event_btn_save_exitActionPerformed

    private void btn_removeClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeClientActionPerformed
        OpenRemoveClientDialog();
    }//GEN-LAST:event_btn_removeClientActionPerformed

    public final void showClients() {
        HashMap<String, Client> allClients = this.getOrganization().getAllClients();
        if (!allClients.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) clientList.getModel();
            model.setRowCount(0);
            for (Client c : allClients.values()) {
                addClientRow(c.getRow(), model);
            }
            clientList.setModel(model);
        }
    }

    // Method to open the dialog for adding a new client
    public void openAddClientDialog() {
        addClient addClientPanel = new addClient(this.getOrganization(), this); // Assume this is a JPanel for adding clients
        this.getHome().showDialogPanel(addClientPanel, "Add Client");
    }

    // Method to open the dialog for displaying client information
    public void openClientInfoDialog(Client client) {
        ClientInfo clientInfoPanel = new ClientInfo(client, this.getOrganization(), this); // Assume this is a JPanel for displaying client info
        this.getHome().showDialogPanel(clientInfoPanel, "Client Info");
    }

    public void addClientRow(Object[] clientRecord, DefaultTableModel model) {
        model.addRow(clientRecord);
        clientList.setModel(model);
    }

    public void addClientRow(Object[] clientRecord) {
        DefaultTableModel model = (DefaultTableModel) clientList.getModel();
        model.addRow(clientRecord);
        clientList.setModel(model);
    }

    private boolean performSearch(String searchValue, JComboBox jcb_parameter) {
        Client searchClient = null;
        if (jcb_parameter.getSelectedIndex() == 0) { // Search by full name
            searchClient = this.getOrganization().searchClientByName(searchValue);
        } else if (jcb_parameter.getSelectedIndex() == 1) { // Search by ID
            searchClient = this.getOrganization().searchClientByID(searchValue);
        }   
        if (searchClient != null) {
            openClientInfoDialog(searchClient);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Client "+searchValue+" not found.");
            return false;
        }
    }
    private void saveExit() {
        boolean saved = false;
        if (!this.getAdded().isEmpty()) {
            saved = saveAddedClients();
        }
        if (!this.getRemoved().isEmpty()) {
            saved = saveRemovedClients();
        }
        if (saved) {
            JOptionPane.showMessageDialog(null, "Successfully saved all clients!");
        }
    }

    public boolean saveRemovedClients() {
        String sqlRemove = "DELETE FROM client WHERE client_id = ?";

        try (Connection connection = Db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlRemove)) {

            connection.setAutoCommit(false);
            while (!this.getRemoved().isEmpty()) {
                String client_id = this.getRemoved().pop();
                preparedStatement.setString(1, client_id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException sqlx) {
            JOptionPane.showMessageDialog(null, "Could not properly remove clients: " + sqlx.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveAddedClients() {
        String sqlInsert = "INSERT INTO clients (client_id, client_name, phone, email, branch_id, assigned_to) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Db.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
                while (!this.getAdded().isEmpty()) {
                    String id = this.getAdded().pop();
                    Client client = this.getOrganization().searchClientByID(id);
                    if (client != null) {
                        preparedStatement.setString(1, client.getClient_id());
                        preparedStatement.setString(2, client.getName());
                        preparedStatement.setString(3, client.getPhone_number());
                        preparedStatement.setString(4, client.getEmail());
                        preparedStatement.setInt(5, client.getBranch_id());
                        preparedStatement.setString(6, client.getAssigned_to());
                        preparedStatement.addBatch();
                    }
                }
                preparedStatement.executeBatch();
                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback in case of error
                throw e; // Rethrow exception to handle it outside
            }
        } catch (SQLException sqx) {
            JOptionPane.showMessageDialog(null, "Could not properly add clients: " + sqx.getMessage());
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    public void OpenRemoveClientDialog() {
        btn_removeClient.setEnabled(false);
        JDialog jd = new JDialog();
        jd.setTitle("Remove Client");
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btn_removeClient.setEnabled(true);
            }
        });

        JComboBox<String> box = new JComboBox();
        box.addItem("by full name");
        box.addItem("by id");   
        jd.setSize(WIDTH, HEIGHT);
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        JTextField searchField = new JTextField(20);
        
        JButton btn = new JButton("Search");
        btn.addActionListener(e -> {
            String v = searchField.getText().trim();
            boolean isFound = performSearch(v, box);
            if (isFound) {
                jd.dispose();
            } else {
                searchField.setText("");
            }
        });
        JButton closebtn = new JButton("Close");
        closebtn.addActionListener(e -> {
            jd.dispose();
        });
        jp.add(searchField);
        jp.add(box);
        jp.add(btn);
        jp.add(closebtn);
        jd.add(jp);
        jd.pack(); // Adjust dialog size based on contents
        jd.setLocationRelativeTo(null); // Center dialog on screen
        jd.setVisible(true);        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addClient;
    private javax.swing.JButton btn_removeClient;
    private javax.swing.JButton btn_save_exit;
    private javax.swing.JButton btn_search;
    private javax.swing.JTable clientList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_search_parameter;
    private javax.swing.JLabel lbl_clientRecords;
    private javax.swing.JTextField tf_searchField;
    // End of variables declaration//GEN-END:variables

}
