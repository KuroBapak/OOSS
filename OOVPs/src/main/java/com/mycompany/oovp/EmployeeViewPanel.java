package com.mycompany.oovp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeViewPanel extends JPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable tblInfo;
    // End of variables declaration//GEN-END:variables

    private int empId;

    public EmployeeViewPanel() {
        initComponents();
    }

    /**
     * Sets the current employee ID and refreshes the view.
     */
    public void setEmployee(int empId) {
        this.empId = empId;
        loadInfo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        tblInfo = new JTable();
        tblInfo.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[]{"Month", "Days Missed", "Status", "Claimable"}
        ) {
            Class<?>[] types = new Class<?>[]{java.sql.Date.class, Integer.class, String.class, java.math.BigDecimal.class};
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(tblInfo), BorderLayout.CENTER);
    }
    // </editor-fold>//GEN-END:initComponents

    /**
     * Loads absence data for the current employee.
     */
    private void loadInfo() {
        DefaultTableModel model = (DefaultTableModel) tblInfo.getModel();
        model.setRowCount(0);

        String sql =
            "SELECT a.month, a.days_missed, a.status, (e.Salary - a.days_missed * 1000) AS claimable " +
            "FROM absences a " +
            "JOIN employees e ON a.emp_id = e.ID " +
            "WHERE a.emp_id = ? " +
            "ORDER BY a.month";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getDate("month"),
                    rs.getInt("days_missed"),
                    rs.getString("status"),
                    rs.getBigDecimal("claimable")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading absences: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
