/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author moren
 */

public class User {
    private int userId;
    private String username;
    private String role;      // 'Admin', 'HRD', 'Employee'
    private Integer empId;    // nullable for Admins

    public User(int userId, String username, String role, Integer empId) {
        this.userId   = userId;
        this.username = username;
        this.role     = role;
        this.empId    = empId;
    }
    // getters/setters...
        // Getters
    public int    getUserId()  { return userId; }
    public String getUsername(){ return username; }
    public String getRole()    { return role; }
    public int    getEmpId()   { return empId; }
}

