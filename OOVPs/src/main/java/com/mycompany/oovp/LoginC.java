/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oovp;

/**
 *
 * @author moren
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.HRD;

public class LoginC {

    /**
     * Attempts to log in with the given credentials.
     * Returns a User object on success, or null if the username/password don't match.
     */
    public HRD login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=? AND password=SHA2(?,256)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HRD(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getInt("emp_id")
                );
            }
            return null;
        }
    }
}


