package org.cragdatabase.models;

import org.cragdatabase.models.enums.BoulderGrades;
import org.cragdatabase.models.enums.DangerLevel;
import org.cragdatabase.models.enums.Discipline;

import java.util.Date;
import java.util.Objects;

public class Route extends GeographicLayer {

    private int areaId;
    private BoulderGrades grade;
    private DangerLevel danger;
    private int quality;
    private Boolean isClassic;
    private int height;
    private Discipline discipline;
    private String faName;
    private Date faDate;
    private String startPosition;
    private String protection;
    private String directions;

    public Route() {
        super();
    }

    public Route(int id, String name, int areaId, String description, String startPosition) {
        super(id, name, description);
        this.areaId = areaId;
        this.startPosition = startPosition;
    }

    public Route(int id, String name, int areaId, String description, String directions, String protection, String startPosition, Date faDate, String faName, int height, Discipline discipline, BoulderGrades grade, DangerLevel danger, int quality, Boolean isClassic) {
        this.areaId = areaId;
        this.directions = directions;
        this.protection = protection;
        this.startPosition = startPosition;
        this.faDate = faDate;
        this.faName = faName;
        this.height = height;
        this.discipline = discipline;
        this.grade = grade;
        this.danger = danger;
        this.quality = quality;
        this.isClassic = isClassic;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public BoulderGrades getGrade() {
        return grade;
    }

    public void setGrade(BoulderGrades grade) {
        this.grade = grade;
    }

    public DangerLevel getDanger() {
        return danger;
    }

    public void setDanger(DangerLevel danger) {
        this.danger = danger;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Boolean getClassic() {
        return isClassic;
    }

    public void setClassic(Boolean classic) {
        isClassic = classic;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public Date getFaDate() {
        return faDate;
    }

    public void setFaDate(Date faDate) {
        this.faDate = faDate;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id && areaId == route.areaId && quality == route.quality && height == route.height && Objects.equals(name, route.name) && grade == route.grade && danger == route.danger && Objects.equals(isClassic, route.isClassic) && discipline == route.discipline && Objects.equals(faName, route.faName) && Objects.equals(faDate, route.faDate) && Objects.equals(description, route.description) && Objects.equals(startPosition, route.startPosition) && Objects.equals(protection, route.protection) && Objects.equals(directions, route.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, areaId, grade, danger, quality, isClassic, height, discipline, faName, faDate, description, startPosition, protection, directions);
    }

    @Override
    public String toString() {
        return "Route{" +
                ", areaId=" + areaId +
                ", grade=" + grade +
                ", danger=" + danger +
                ", quality=" + quality +
                ", isClassic=" + isClassic +
                ", height=" + height +
                ", discipline=" + discipline +
                ", faName='" + faName + '\'' +
                ", faDate=" + faDate +
                ", startPosition='" + startPosition + '\'' +
                ", protection='" + protection + '\'' +
                ", directions='" + directions + '\'' +
                '}';
    }
}
