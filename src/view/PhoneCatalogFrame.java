/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.PhoneController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.util.regex.Pattern;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import util.PhoneStorage;

/**
 *
 * @author USER
 */
public class PhoneCatalogFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PhoneCatalogFrame.class.getName());

    public PhoneCatalogFrame() {
        initComponents();
        setupPhoneTable();
        setupSorting();
        setupSearch();
        phoneController = new PhoneController();
        getContentPane().setBackground(
        new java.awt.Color(230, 240, 250));
        
        btnAdd.setBackground(new java.awt.Color(25, 135, 84));
        btnAdd.setForeground(java.awt.Color.WHITE);
        btnAdd.setOpaque(true);
        btnAdd.setContentAreaFilled(true);
        btnAdd.setBorderPainted(false);
        
        btnRemove.setBackground(new java.awt.Color(220, 53, 69));
        btnRemove.setForeground(java.awt.Color.WHITE);
        btnRemove.setOpaque(true);
        btnRemove.setContentAreaFilled(true);
        btnRemove.setBorderPainted(false);

    }
    
    private PhoneController phoneController;
    private DefaultTableModel phoneModel;
    private TableRowSorter<DefaultTableModel> sorter;

private void setupPhoneTable() {
    phoneModel = new DefaultTableModel(
        new Object[]{"Brand", "Model",
                     "Date Released", "Price (PHP)"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int column) {
            return column == 3 ? Integer.class : String.class;
        }
    };

   boolean loaded = PhoneStorage.loadPhones(phoneModel);

if (!loaded) {
    phoneModel.addRow(new Object[]{
        "Apple", "iPhone 16", "2024-09-20", 49990
    });

    phoneModel.addRow(new Object[]{
        "Samsung", "Galaxy S25", "2025-02-07", 47990
    });

    phoneModel.addRow(new Object[]{
        "Google", "Pixel 9", "2024-08-22", 39990
    });

    PhoneStorage.savePhones(phoneModel);
}

    tblPhones.setModel(phoneModel);
}

private void setupSorting() {
   sorter = new TableRowSorter<>(phoneModel);
    tblPhones.setRowSorter(sorter);
    
    sorter.setComparator(0, String.CASE_INSENSITIVE_ORDER);

    cmbSort.addActionListener(e -> {
        Object selected = cmbSort.getSelectedItem();

        if (selected == null) {
            return;
        }

        String choice = selected.toString();

        switch (choice) {
            case "Default Order":
                sorter.setSortKeys(null);
                break;

            case "Brand (A-Z)":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(0, SortOrder.ASCENDING)
                ));
                break;

            case "Brand (Z-A)":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(0, SortOrder.DESCENDING)
                ));
                break;

            case "Price: Low to High":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(3, SortOrder.ASCENDING)
                ));
                break;

            case "Price: High to Low":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(3, SortOrder.DESCENDING)
                ));
                break;

            case "Model (A-Z)":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(1, SortOrder.ASCENDING)
                ));
                break;

            case "Newest Release":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(2, SortOrder.DESCENDING)
                ));
                break;

            case "Oldest Release":
                sorter.setSortKeys(java.util.Collections.singletonList(
                    new RowSorter.SortKey(2, SortOrder.ASCENDING)
                ));
                break;

            default:
                System.out.println("Unknown sorting option: " + choice);
                break;
        }
    });
}

private void setupSearch() {
    txtSearch.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            private void filterPhones() {
                String text = txtSearch.getText().trim();

                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(
                        RowFilter.regexFilter(
                            "(?i)" + Pattern.quote(text), 0, 1
                        )
                    );
                }
            }

            @Override
            public void insertUpdate(
                    javax.swing.event.DocumentEvent e) {
                filterPhones();
            }

            @Override
            public void removeUpdate(
                    javax.swing.event.DocumentEvent e) {
                filterPhones();
            }

            @Override
            public void changedUpdate(
                    javax.swing.event.DocumentEvent e) {
                filterPhones();
            }
        }
    );
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmbSort = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("PocketCatalog");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Phone Inventory System");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        cmbSort.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default Order", "Brand (A-Z)", "Brand (Z-A)", "Newest Release", "Oldest Release", "Price: Low to High", "Price: High to Low" }));
        cmbSort.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbSort.addActionListener(this::cmbSortActionPerformed);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setText("Add Phone");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(0, 0, 0));
        btnRemove.setText("Remove Phone");
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemove.addActionListener(this::btnRemoveActionPerformed);

        tblPhones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblPhones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Brand", "Model", "Date Released", "Price (PHP)"
            }
        ));
        tblPhones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tblPhones);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Browse, Search, sort and manage smartphone record and prices");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        phoneController.addPhone(phoneModel);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
       phoneController.removePhone(tblPhones, phoneModel);
       
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSortActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PhoneCatalogFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhones;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
