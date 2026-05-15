package org.cragdatabase.models;

import org.cragdatabase.models.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String displayName;
    private String username;
    private String password;
    private Role role;
    private int locationId;
    private List<Integer> listIds;

    public User(int id, String displayName, String username, String password, Role role, int locationId) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locationId = locationId;
        this.listIds = new ArrayList<>();
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public List<Integer> getListIds() {
        return listIds;
    }

    public void setListIds(List<Integer> listIds) {
        this.listIds = listIds;
    }
}
