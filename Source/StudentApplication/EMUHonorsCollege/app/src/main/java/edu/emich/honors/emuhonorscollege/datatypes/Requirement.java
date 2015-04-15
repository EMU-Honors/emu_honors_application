package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;

/**
 * Created by stefano on 3/21/15.
 * 4/15 -> updated to decided attributes of requirements
 */
public class Requirement{

    private String year;
    private String type;
    private String dispNumber;
    private String name;
    private String component;
    private int totalNeeded;  //needed for completion
    private String description;
    private int completed;  //number completed -> will test if completed == totalNeeded


    public Requirement() {
        this.year = "";
        this.type = "";
        this.dispNumber = "";
        this.name = "";
        this.component = "";
        this.totalNeeded = 0;
        this.description = "";
        this.completed = 0;
    }

    public Requirement(String year, String type, String dispNumber, String name,
                       String component, int totalNeeded, String description, int completed) {
        this();
        this.year = year;
        this.type = type;
        this.dispNumber = dispNumber;
        this.name = name;
        this.component = component;
        this.totalNeeded = totalNeeded;
        this.description = description;
        this.completed = completed;
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDispNumber() {
        return dispNumber;
    }

    public void setDispNumber(String dispNumber) {
        this.dispNumber = dispNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getTotalNeeded() {
        return totalNeeded;
    }

    public void setTotalNeeded(int totalNeeded) {
        this.totalNeeded = totalNeeded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }


}