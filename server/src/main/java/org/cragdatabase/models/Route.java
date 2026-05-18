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
}
