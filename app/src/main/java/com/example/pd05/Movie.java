package com.example.pd05;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String year;
    private int release;
    private int stars;

    public Movie(int id, String title, String year, int release, int stars){
        this.id = id;
        this.title = title;
        this.year = year;
        this.release = release;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ID:" + id + ", " + "Title: " + title + ", " + "Year: " + year + ", " + "Release: " + release + ", " + "Stars: " +stars;
    }

}
