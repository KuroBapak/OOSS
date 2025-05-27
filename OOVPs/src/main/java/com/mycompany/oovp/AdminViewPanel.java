package com.mycompany.oovp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;

public class AdminViewPanel extends JPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton            btnAddAdmin;
    private JButton            btnAddEmp;
    private JButton            btnDelAdmin;
    private JButton            btnDelEmp;
    private JButton            btnEditAdmin;
    private JButton            btnEditEmp;
    private JButton            btnFilter;
    private JComboBox<String>  cmbDeptFilter;
    private JTextField         txtSalaryMin;
    private JTextField         txtSalaryMax;
    private JLabel             lblDept;
    private JLabel             lblSalary;
    private JPanel             jPanel1;
    private JPanel             jPanel2;
    private JScrollPane        jScrollPane1;
    private JScrollPane        jScrollPane2;
    private JTabbedPane        jTabbedPane1;
    private JTable             jTable1;
    private JTable             tblEmployees;
    // End of variables declaration//GEN-END:variables

    public AdminViewPanel() {
        initComponents();
        initCustom();
    }

    private void initCustom() {
        // Employees tab
        DefaultTableModel empModel = (DefaultTableModel) tblEmployees.getModel();
        loadEmployees(empModel);
        btnFilter .addActionListener(e -> applyFilter(empModel));
        btnAddEmp .addActionListener(e -> onAddEmp(empModel));
        btnEditEmp.addActionListener(e -> onEditEmp(empModel));
        btnDelEmp .addActionListener(e -> onDelEmp(empModel));

        // Admins tab
        DefaultTableModel admModel = (DefaultTableModel) jTable1.getModel();
        loadAdmins(admModel);
        btnAddAdmin .addActionListener(e -> onAddAdmin(admModel));
        btnEditAdmin.addActionListener(e -> onEditAdmin(admModel));
        btnDelAdmin .addActionListener(e -> onDelAdmin(admModel));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTabbedPane1     = new JTabbedPane();
        jPanel1         = new JPanel();
        jScrollPane1    = new JScrollPane();
        tblEmployees    = new JTable();
        btnAddEmp       = new JButton("Add");
        btnEditEmp      = new JButton("Edit");
        btnDelEmp       = new JButton("Delete");
        lblDept         = new JLabel("Dept:");
        cmbDeptFilter   = new JComboBox<>(new String[]{"All","IT","HR","ACC","FINANCE"});
        lblSalary       = new JLabel("Salary:");
        txtSalaryMin    = new JTextField();
        txtSalaryMax    = new JTextField();
        btnFilter       = new JButton("Filter");
        jPanel2         = new JPanel();
        jScrollPane2    = new JScrollPane();
        jTable1         = new JTable();
        btnAddAdmin     = new JButton("Add");
        btnEditAdmin    = new JButton("Edit");
        btnDelAdmin     = new JButton("Delete");

        // Employees table
        tblEmployees.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[]{"ID","Name","Dept","Salary","Address","Card","Type"}
        ));
        jScrollPane1.setViewportView(tblEmployees);

        // Layout for Employees tab
        GroupLayout panel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDept)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDeptFilter, 100,100,100)
                .addGap(18)
                .addComponent(lblSalary)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSalaryMin, 80,80,80)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSalaryMax, 80,80,80)
                .addGap(18)
                .addComponent(btnFilter)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnAddEmp)
                .addGap(8)
                .addComponent(btnEditEmp)
                .addGap(8)
                .addComponent(btnDelEmp)
                .addContainerGap()
            )
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDept)
                    .addComponent(cmbDeptFilter)
                    .addComponent(lblSalary)
                    .addComponent(txtSalaryMin)
                    .addComponent(txtSalaryMax)
                    .addComponent(btnFilter)
                    .addComponent(btnAddEmp)
                    .addComponent(btnEditEmp)
                    .addComponent(btnDelEmp)
                )
                .addContainerGap(20, Short.MAX_VALUE)
            )
        );
        jTabbedPane1.addTab("Employees", jPanel1);

        // Admins table
        jTable1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[]{"UserID","Username","Role","Emp ID"}
        ));
        jScrollPane2.setViewportView(jTable1);

        // Layout for Admins tab
        GroupLayout panel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddAdmin)
                .addGap(8)
                .addComponent(btnEditAdmin)
                .addGap(8)
                .addComponent(btnDelAdmin)
                .addContainerGap(Short.MAX_VALUE, Short.MAX_VALUE)
            )
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAdmin)
                    .addComponent(btnEditAdmin)
                    .addComponent(btnDelAdmin)
                )
                .addContainerGap(20, Short.MAX_VALUE)
            )
        );
        jTabbedPane1.addTab("Admins", jPanel2);

        // Main panel layout
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }
    // </editor-fold>//GEN-END:initComponents

    private void loadEmployees(DefaultTableModel model) {
        model.setRowCount(0);
        String sql = "SELECT ID,Name,Department,Salary,address,pay_card,emp_type FROM employees";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Department"),
                    rs.getBigDecimal("Salary"),
                    rs.getString("address"),
                    rs.getString("pay_card"),
                    rs.getString("emp_type")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading employees: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyFilter(DefaultTableModel model) {
        model.setRowCount(0);
        String dept = (String) cmbDeptFilter.getSelectedItem();
        String min  = txtSalaryMin.getText().trim();
        String max  = txtSalaryMax.getText().trim();

        StringBuilder sql = new StringBuilder(
            "SELECT ID,Name,Department,Salary,address,pay_card,emp_type FROM employees WHERE 1=1"
        );
        if (!"All".equals(dept)) sql.append(" AND Department=?");
        if (!min.isEmpty())      sql.append(" AND Salary>=?");
        if (!max.isEmpty())      sql.append(" AND Salary<=?");

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {
            int idx = 1;
            if (!"All".equals(dept)) ps.setString(idx++, dept);
            if (!min.isEmpty())      ps.setBigDecimal(idx++, new BigDecimal(min));
            if (!max.isEmpty())      ps.setBigDecimal(idx++, new BigDecimal(max));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Department"),
                    rs.getBigDecimal("Salary"),
                    rs.getString("address"),
                    rs.getString("pay_card"),
                    rs.getString("emp_type")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error applying filter: " + ex.getMessage(),
                "Filter Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAdmins(DefaultTableModel model) {
        model.setRowCount(0);
        String sql = "SELECT user_id,username,role,emp_id FROM users WHERE role IN('Admin','HRD')";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getObject("emp_id")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading admins: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- CRUD Handlers for Employees ---
    private void onAddEmp(DefaultTableModel model) {
        JTextField name    = new JTextField();
        JComboBox<String> cmbDeptDialog = new JComboBox<>(new String[]{"IT","HR","ACC","FINANCE"});
        JTextField salF    = new JTextField();
        JTextField addrF   = new JTextField();
        JTextField cardF   = new JTextField();
        JComboBox<String> typeF = new JComboBox<>(new String[]{"Employee","Contract"});
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Name:"));       panel.add(name);
        panel.add(new JLabel("Department:")); panel.add(cmbDeptDialog);
        panel.add(new JLabel("Salary:"));     panel.add(salF);
        panel.add(new JLabel("Address:"));    panel.add(addrF);
        panel.add(new JLabel("Pay Card:"));   panel.add(cardF);
        panel.add(new JLabel("Type:"));       panel.add(typeF);

        if (JOptionPane.showConfirmDialog(this, panel, "Add Employee",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String sql = "INSERT INTO employees(Name,Department,Salary,address,pay_card,emp_type) VALUES(?,?,?,?,?,?)";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, name.getText());
                ps.setString(2, (String) cmbDeptDialog.getSelectedItem());
                ps.setBigDecimal(3, new BigDecimal(salF.getText()));
                ps.setString(4, addrF.getText());
                ps.setString(5, cardF.getText());
                ps.setString(6, (String) typeF.getSelectedItem());
                ps.executeUpdate();
                loadEmployees(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error adding employee: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onEditEmp(DefaultTableModel model) {
        int row = tblEmployees.getSelectedRow();
        if (row < 0) return;
        Integer id = (Integer) model.getValueAt(row, 0);

        JTextField name    = new JTextField((String) model.getValueAt(row,1));
        JComboBox<String> cmbDeptDialog = new JComboBox<>(new String[]{"IT","HR","ACC","FINANCE"});
        cmbDeptDialog.setSelectedItem(model.getValueAt(row,2));
        JTextField salF    = new JTextField(model.getValueAt(row,3).toString());
        JTextField addrF   = new JTextField((String) model.getValueAt(row,4));
        JTextField cardF   = new JTextField((String) model.getValueAt(row,5));
        JComboBox<String> typeF = new JComboBox<>(new String[]{"Employee","Contract"});
        typeF.setSelectedItem(model.getValueAt(row,6));

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Name:"));       panel.add(name);
        panel.add(new JLabel("Department:")); panel.add(cmbDeptDialog);
        panel.add(new JLabel("Salary:"));     panel.add(salF);
        panel.add(new JLabel("Address:"));    panel.add(addrF);
        panel.add(new JLabel("Pay Card:"));   panel.add(cardF);
        panel.add(new JLabel("Type:"));       panel.add(typeF);

        if (JOptionPane.showConfirmDialog(this, panel, "Edit Employee",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String sql = "UPDATE employees SET Name=?,Department=?,Salary=?,address=?,pay_card=?,emp_type=? WHERE ID=?";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, name.getText());
                ps.setString(2, (String) cmbDeptDialog.getSelectedItem());
                ps.setBigDecimal(3, new BigDecimal(salF.getText()));
                ps.setString(4, addrF.getText());
                ps.setString(5, cardF.getText());
                ps.setString(6, (String) typeF.getSelectedItem());
                ps.setInt(7, id);
                ps.executeUpdate();
                loadEmployees(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error updating employee: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onDelEmp(DefaultTableModel model) {
        int row = tblEmployees.getSelectedRow();
        if (row < 0) return;
        Integer id = (Integer) model.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this,
            "Delete employee ID " + id + "?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM employees WHERE ID=?";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                loadEmployees(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error deleting employee: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // --- CRUD Handlers for Admins ---
    private void onAddAdmin(DefaultTableModel model) {
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Admin","HRD"});
        JTextField empIdF = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Username:")); panel.add(username);
        panel.add(new JLabel("Password:")); panel.add(password);
        panel.add(new JLabel("Role:"));     panel.add(roleBox);
        panel.add(new JLabel("Emp ID (opt):")); panel.add(empIdF);

        if (JOptionPane.showConfirmDialog(this, panel, "Add Admin",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String sql = "INSERT INTO users(username,password,role,emp_id) VALUES(?,SHA2(?,256),?,?)";
            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, username.getText());
                ps.setString(2, new String(password.getPassword()));
                ps.setString(3, (String) roleBox.getSelectedItem());
                if (empIdF.getText().trim().isEmpty()) ps.setNull(4, Types.INTEGER);
                else ps.setInt(4, Integer.parseInt(empIdF.getText()));
                ps.executeUpdate();
                loadAdmins(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error adding admin: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onEditAdmin(DefaultTableModel model) {
        int row = jTable1.getSelectedRow(); if (row<0) return;
        Integer id = (Integer) model.getValueAt(row, 0);
        String currUser = (String) model.getValueAt(row, 1);
        String currRole = (String) model.getValueAt(row, 2);
        Object  currEmp  = model.getValueAt(row, 3);

        JTextField username = new JTextField(currUser);
        JPasswordField password = new JPasswordField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Admin","HRD"});
        roleBox.setSelectedItem(currRole);
        JTextField empIdF = new JTextField(currEmp==null?"":currEmp.toString());

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Username:")); panel.add(username);
        panel.add(new JLabel("Password (leave blank to keep):")); panel.add(password);
        panel.add(new JLabel("Role:"));     panel.add(roleBox);
        panel.add(new JLabel("Emp ID (opt):")); panel.add(empIdF);

        if (JOptionPane.showConfirmDialog(this, panel, "Edit Admin",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            StringBuilder sql = new StringBuilder(
                "UPDATE users SET username=?,role=?,emp_id=?"
            );
            if (password.getPassword().length>0) sql.append(",password=SHA2(?,256)");
            sql.append(" WHERE user_id=?");

            try (Connection c = DBConnection.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql.toString())) {
                int idx=1;
                ps.setString(idx++, username.getText());
                ps.setString(idx++, (String) roleBox.getSelectedItem());
                if (empIdF.getText().trim().isEmpty()) ps.setNull(idx++, Types.INTEGER);
                else ps.setInt(idx++, Integer.parseInt(empIdF.getText()));
                if (password.getPassword().length>0)
                    ps.setString(idx++, new String(password.getPassword()));
                ps.setInt(idx, id);
                ps.executeUpdate();
                loadAdmins(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error editing admin: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onDelAdmin(DefaultTableModel model) {
        int row = jTable1.getSelectedRow(); if (row<0) return;
        Integer id = (Integer) model.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this,
            "Delete user ID " + id + "?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            String sql="DELETE FROM users WHERE user_id=?";
            try (Connection c=DBConnection.getConnection();
                 PreparedStatement ps=c.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                loadAdmins(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error deleting admin: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
