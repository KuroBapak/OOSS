package com.mycompany.oovp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HRDViewPanel extends JPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStatus;
    private javax.swing.JButton btnDelStatus;
    private javax.swing.JButton btnEditStatus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblStatus;
    // End of variables declaration//GEN-END:variables

    public HRDViewPanel() {
        initComponents();
        initCustom();
    }

    private void initCustom() {
        DefaultTableModel model = (DefaultTableModel) tblStatus.getModel();
        loadStatus(model);

        btnRefresh.addActionListener(e -> loadStatus(model));
        btnAddStatus.addActionListener(e -> onAdd(model));
        btnEditStatus.addActionListener(e -> onEdit(model));
        btnDelStatus.addActionListener(e -> onDelete(model));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1       = new javax.swing.JPanel();
        btnAddStatus  = new javax.swing.JButton();
        btnEditStatus = new javax.swing.JButton();
        btnDelStatus  = new javax.swing.JButton();
        jScrollPane2  = new javax.swing.JScrollPane();
        tblStatus     = new javax.swing.JTable();
        btnRefresh    = new javax.swing.JButton();

        btnAddStatus.setText("Add");
        btnEditStatus.setText("Edit");
        btnDelStatus.setText("Delete");
        btnRefresh.setText("Refresh");

        tblStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Absence ID", "Emp ID", "Employee", "Month",
                "Days Missed", "Status", "Claimable"
            }
        ) {
            Class<?>[] types = new Class<?>[] {
                Integer.class, Integer.class, String.class,
                java.sql.Date.class, Integer.class,
                String.class, java.math.BigDecimal.class
            };
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        jScrollPane2.setViewportView(tblStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
          jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2)
          .addGroup(jPanel1Layout.createSequentialGroup()
              .addContainerGap()
              .addComponent(btnAddStatus)
              .addGap(18)
              .addComponent(btnEditStatus)
              .addGap(18)
              .addComponent(btnDelStatus)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
              .addComponent(btnRefresh)
              .addContainerGap()
          )
        );
        jPanel1Layout.setVerticalGroup(
          jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
              .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(12)
              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(btnAddStatus)
                  .addComponent(btnEditStatus)
                  .addComponent(btnDelStatus)
                  .addComponent(btnRefresh)
              )
              .addContainerGap(20, Short.MAX_VALUE)
          )
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    // </editor-fold>//GEN-END:initComponents

    private void loadStatus(DefaultTableModel model) {
        model.setRowCount(0);
        String sql =
            "SELECT a.absence_id, a.emp_id, e.Name, a.month, a.days_missed, a.status, " +
            "(e.Salary - a.days_missed * 1000) AS claimable " +
            "FROM absences a " +
            "JOIN employees e ON a.emp_id = e.ID " +
            "ORDER BY a.emp_id, a.month";

        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("absence_id"),
                    rs.getInt("emp_id"),
                    rs.getString("Name"),
                    rs.getDate("month"),
                    rs.getInt("days_missed"),
                    rs.getString("status"),
                    rs.getBigDecimal("claimable")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading HRD data: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onAdd(DefaultTableModel model) {
        JTextField empF   = new JTextField();
        JTextField monF   = new JTextField("YYYY-MM-DD");
        JTextField daysF  = new JTextField();
        JComboBox<String> stF = new JComboBox<>(new String[]{"Not Yet","Done"});
        JPanel p = new JPanel(new GridLayout(0,1));
        p.add(new JLabel("Emp ID:"));        p.add(empF);
        p.add(new JLabel("Month (YYYY‑MM‑DD):")); p.add(monF);
        p.add(new JLabel("Days Missed:"));   p.add(daysF);
        p.add(new JLabel("Status:"));        p.add(stF);

        if (JOptionPane.showConfirmDialog(this, p, "Add Absence",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String sql = "INSERT INTO absences(emp_id,month,days_missed,status) VALUES(?,?,?,?)";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, Integer.parseInt(empF.getText()));
                ps.setDate(2, Date.valueOf(monF.getText()));
                ps.setInt(3, Integer.parseInt(daysF.getText()));
                ps.setString(4, (String) stF.getSelectedItem());
                ps.executeUpdate();
                loadStatus(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error adding absence: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void onEdit(DefaultTableModel model) {
        int row = tblStatus.getSelectedRow();
        if (row < 0) return;

        Integer id    = (Integer) model.getValueAt(row, 0);
        Integer days  = (Integer) model.getValueAt(row, 4);
        String  stat  = (String)  model.getValueAt(row, 5);

        JTextField daysF = new JTextField(days.toString());
        JComboBox<String> stF = new JComboBox<>(new String[]{"Not Yet","Done"});
        stF.setSelectedItem(stat);
        JPanel p = new JPanel(new GridLayout(0,1));
        p.add(new JLabel("Days Missed:")); p.add(daysF);
        p.add(new JLabel("Status:"));      p.add(stF);

        if (JOptionPane.showConfirmDialog(this, p, "Edit Absence",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String sql = "UPDATE absences SET days_missed=?,status=? WHERE absence_id=?";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, Integer.parseInt(daysF.getText()));
                ps.setString(2, (String) stF.getSelectedItem());
                ps.setInt(3, id);
                ps.executeUpdate();
                loadStatus(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error updating absence: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void onDelete(DefaultTableModel model) {
        int row = tblStatus.getSelectedRow();
        if (row < 0) return;

        Integer id = (Integer) model.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this,
            "Delete absence #" + id + "?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM absences WHERE absence_id=?";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                loadStatus(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error deleting absence: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
