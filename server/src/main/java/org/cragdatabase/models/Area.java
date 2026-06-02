package org.cragdatabase.models;

import java.util.List;

public class Area extends GeographicLayer {

    private int superAreaId;
    private int cragId;
    private List<Area> subareas;
    private List<Route> routes;

    public Area() {
        super();
    }

    public Area(int id, String name, int superAreaId, int cragId, String description) {
        super(id, name, description);
        this.superAreaId = superAreaId;
        this.cragId = cragId;
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
