package com.mycompany.oovp;

import model.User;
import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel cardPanel;
    // End of variables declaration//GEN-END:variables

    private AdminViewPanel    adminPanel;
    private HRDViewPanel      hrdPanel;
    private EmployeeViewPanel empPanel;
    private User              currentUser;

    public MainJFrame()      { initComponents(); setupCards(); }
    public MainJFrame(User u) {
        this.currentUser = u;
        initComponents();
        setupCards();
        showRole();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        cardPanel = new JPanel(new CardLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Management");
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>//GEN-END:initComponents

    private void setupCards() {
        adminPanel = new AdminViewPanel();
        hrdPanel   = new HRDViewPanel();
        empPanel   = new EmployeeViewPanel();
        CardLayout cl = (CardLayout)cardPanel.getLayout();
        cardPanel.add(adminPanel, "ADMIN");
        cardPanel.add(hrdPanel,   "HRD");
        cardPanel.add(empPanel,   "EMP");
    }

    private void showRole() {
        CardLayout cl = (CardLayout)cardPanel.getLayout();
        String role = currentUser.getRole();
        if ("Admin".equalsIgnoreCase(role)) {
            cl.show(cardPanel, "ADMIN");
        } else if ("HRD".equalsIgnoreCase(role)) {
            cl.show(cardPanel, "HRD");
        } else {
            empPanel.setEmployee(currentUser.getEmpId());
            cl.show(cardPanel, "EMP");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginDialog dlg = new LoginDialog(null, true);
            dlg.setVisible(true);
            User u = dlg.getAuthenticatedUser();
            if (u!=null) new MainJFrame(u).setVisible(true);
            else System.exit(0);
        });
    }
}
