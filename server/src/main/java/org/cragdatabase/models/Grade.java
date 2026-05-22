package org.cragdatabase.models;

import org.cragdatabase.models.enums.BoulderGrades;
import org.cragdatabase.models.enums.RopeGrades;

public class Grade {

    BoulderGrades boulderGrade;
    RopeGrades ropeGrades;

    public Grade(RopeGrades ropeGrades) {
        this.ropeGrades = ropeGrades;
    }

    public Grade(BoulderGrades boulderGrade) {
        this.boulderGrade = boulderGrade;
    }

    public BoulderGrades getBoulderGrade() {
        return boulderGrade;
    }

    public void setBoulderGrade(BoulderGrades boulderGrade) {
        this.boulderGrade = boulderGrade;
    }

    public RopeGrades getRopeGrades() {
        return ropeGrades;
    }

    public void setRopeGrades(RopeGrades ropeGrades) {
        this.ropeGrades = ropeGrades;
    }
}
