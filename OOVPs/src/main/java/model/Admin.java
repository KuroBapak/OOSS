package model;

public class Admin extends User {
    public Admin(int userId, String username, String role) {
        super(userId, username, role, null);
    }
}
