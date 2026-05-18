package org.cragdatabase.models;

import java.util.List;
import java.util.Objects;

public class Location {

    private int id;
    private String country;
    private String region;
    private String description;
    private List<Crag> crags;

    public Location() {
    }

    public Location(int id, String country, String region, String description) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(country, location.country) && Objects.equals(region, location.region) && Objects.equals(description, location.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, region, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
