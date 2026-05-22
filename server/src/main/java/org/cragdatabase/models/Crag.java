package org.cragdatabase.models;

import java.util.List;

public class Crag {

    private int id;
    private String name;
    private int locationId;
    private String description;
    private List<Area> areas;

    public Crag() {
    }

    public Crag(int id, String name, int locationId, String description) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
