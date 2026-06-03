package org.cragdatabase.models;

public class RouteSummary {

    private int routeSummaryId;
    private int routeId;
    private int userId;
    private int difficultyRating; 
    private int qualityRating; 
    private int dangerRating; 

    public RouteSummary() {
    }

    public RouteSummary(int routeSummaryId, int routeId, int userId, int difficultyRating, int qualityRating, int dangerRating) {
        this.routeSummaryId = routeSummaryId;
        this.routeId = routeId;
        this.userId = userId;
        this.difficultyRating = difficultyRating;
        this.qualityRating = qualityRating;
        this.dangerRating = dangerRating;
    }

    public int getRouteSummaryId() {
        return routeSummaryId;
    }

    public void setRouteSummaryId(int routeSummaryId) {
        this.routeSummaryId = routeSummaryId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public int getQualityRating() {
        return qualityRating;
    }

    public void setQualityRating(int qualityRating) {
        this.qualityRating = qualityRating;
    }

    public int getDangerRating() {
        return dangerRating;
    }

    public void setDangerRating(int dangerRating) {
        this.dangerRating = dangerRating;
    }
}
