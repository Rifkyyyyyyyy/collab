package domain.model;

import roles.Role;

public final class UserModel {
    private final int id;
    private final String username;
    private final String password;
    private final Role role;

 
    public UserModel(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

 
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
