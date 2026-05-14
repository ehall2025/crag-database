package org.cragdatabase;

import org.cragdatabase.models.Location;

import java.util.List;

public class TestHelper {

    public static List<Location> getLocations() {
        return List.of(new Location(0, "United States", "Wisconsin", ""), new Location(0, "United States", "Minnesota", ""));
    }
}
