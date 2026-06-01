package org.cragdatabase.models;

import java.util.List;
import java.util.Objects;

public class Location extends GeographicLayer {

    private String country;
    private String region;
    private List<Crag> crags;

    public Location() {
        super();
    }

    public Location(int id, String country, String region, String description) {
        super(id, region, description);
        this.country = country;
        this.region = region;
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

    public List<Crag> getCrags() {
        return crags;
    }

    public void setCrags(List<Crag> crags) {
        this.crags = crags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return Objects.equals(country, location.country) && Objects.equals(region, location.region) && Objects.equals(crags, location.crags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, region, crags);
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
