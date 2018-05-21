package com.example.bugssolveinc.bangou.model;

/**
 * Created by mxi on 13/10/17.
 */

public class PointTable {

    int id;
    int points;

    public PointTable() {

    }

    public PointTable(int points) {
        this.points = points;
    }

    public PointTable(int id, int points) {
        this.id = id;
        this.points = points;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setPoints(int tag_name) {
        this.points = tag_name;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public int getPoints() {
        return this.points;
    }
}
