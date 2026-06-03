package org.cragdatabase.models;

public class Suggestion {

    private int suggestionId;
    private int routeId;
    private int userId;
    private int difficultyRating; 
    private int qualityRating; 
    private int dangerRating; 

    public Suggestion() {
    }

    public Suggestion(int suggestionId, int routeId, int userId, int difficultyRating, int qualityRating, int dangerRating) {
        this.suggestionId = suggestionId;
        this.routeId = routeId;
        this.userId = userId;
        this.difficultyRating = difficultyRating;
        this.qualityRating = qualityRating;
        this.dangerRating = dangerRating;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
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
