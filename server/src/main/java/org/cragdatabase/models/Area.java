package org.cragdatabase.models;

import java.util.List;

public class Area {

    private int id;
    private String name;
    private int superAreaId;
    private int cragId;
    private String description;
    private List<Area> subareas;
    private List<Route> routes;

    public Area() {
    }

    public Area(int id, String name, int superAreaId, int cragId, String description) {
        this.id = id;
        this.name = name;
        this.superAreaId = superAreaId;
        this.cragId = cragId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSuperAreaId() {
        return superAreaId;
    }

    public void setSuperAreaId(int superAreaId) {
        this.superAreaId = superAreaId;
    }

    public int getCragId() {
        return cragId;
    }

    public void setCragId(int cragId) {
        this.cragId = cragId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Area> getSubareas() {
        return subareas;
    }

    public void setSubareas(List<Area> subareas) {
        this.subareas = subareas;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
