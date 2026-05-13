package org.cragdatabase.models;

import java.util.Objects;

public class Location {

    private String country;
    private String region;
    private String description;

    public Location() {
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
