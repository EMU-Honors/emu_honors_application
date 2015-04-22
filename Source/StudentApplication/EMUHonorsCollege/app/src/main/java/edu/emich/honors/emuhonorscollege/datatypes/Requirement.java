package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;

/**
 * Created by stefano on 3/21/15.
 */
public class Requirement{
    private String name;
    private String description;
    private ArrayList<Requirement> subRequirements;
    private boolean completed;
    private int numberOfCompleted;
    private int numberRequiredForCompletion;
    private int hierarchyLevel;

    public Requirement() {
        this.name = "";
        this.description = "";
        this.subRequirements = new ArrayList<Requirement>();
        this.completed = false;
        this.numberOfCompleted = 0;
        this.numberRequiredForCompletion = 1;
        this.hierarchyLevel = 1;
    }

    public Requirement(String requirementName, String description, int numberRequiredForCompletion) {
        this();
        this.name = requirementName;
        this.description = description;
        this.numberRequiredForCompletion = numberRequiredForCompletion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfCompleted() {
        return numberOfCompleted;
    }

    public void setNumberOfCompleted(int numberOfCompleted) {
        this.numberOfCompleted = numberOfCompleted;
        completed = (this.getNumberOfCompleted() >= this.getNumberRequiredForCompletion());
    }

    public int getNumberRequiredForCompletion() {
        return numberRequiredForCompletion;
    }

    public void setNumberRequiredForCompletion(int numberRequiredForCompletion) {
        this.numberRequiredForCompletion = numberRequiredForCompletion;
        completed = (this.getNumberOfCompleted() >= this.getNumberRequiredForCompletion());
    }

    public ArrayList<Requirement> getSubRequirements() {
        return subRequirements;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void addSubRequirement(Requirement subRequirementToAdd)
    {
        subRequirementToAdd.hierarchyLevel++;
        subRequirements.add(subRequirementToAdd);
    }
}
