package org.cragdatabase.models;

import java.util.List;

public class Crag extends GeographicLayer {

    private int locationId;
    private List<Area> areas;

    public Crag() {
        super();
    }

    public Crag(int id, String name, int locationId, String description) {
        super(id, name, description);
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
