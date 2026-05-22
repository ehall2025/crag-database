package org.cragdatabase.models;

public class ListEntry {

    private int listId;
    private int routeId;

    public ListEntry() {
    }

    public ListEntry(int listId, int routeId) {
        this.listId = listId;
        this.routeId = routeId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
