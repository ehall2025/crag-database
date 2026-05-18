package org.cragdatabase.models;

import org.cragdatabase.models.enums.BoulderGrades;
import org.cragdatabase.models.enums.DangerLevel;
import org.cragdatabase.models.enums.Discipline;

import java.util.Date;

public class Route {

    private int id;
    private String name;
    private int areaId;
    private BoulderGrades grade;
    private DangerLevel danger;
    private int quality;
    private Boolean isClassic;
    private int height;
    private Discipline discipline;
    private String faName;
    private Date faDate;
    private String description;
    private String startPosition;
    private String protection;
    private String directions;

    public Route() {
    }

    public Route(int id, String name, int areaId, String description, String startPosition) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.description = description;
        this.startPosition = startPosition;
    }

    public Route(int id, String name, int areaId, String description, String directions, String protection, String startPosition, Date faDate, String faName, int height, Discipline discipline, BoulderGrades grade, DangerLevel danger, int quality, Boolean isClassic) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
