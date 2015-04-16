package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Requirement{
    private  String name;
    private  String description;
    private ArrayList<Requirement> subRequirements;
    private boolean completed;
    private int numberOfCompleted;
    private  int numberRequiredForCompletion;
    private int hierarchyLevel;
    private LinkedList<String> coachingSteps;
    private LinkedList<String> persistentCoachingSteps;
    private boolean isInProgress = false;

    public Requirement() {
        this.name = "";
        this.description = "";
        this.subRequirements = new ArrayList<>();
        this.completed = false;
        this.numberOfCompleted = 0;
        this.numberRequiredForCompletion = 1;
        this.hierarchyLevel = 1;
        this.coachingSteps = new LinkedList<>();
        this.persistentCoachingSteps = new LinkedList<>();
    }

    public Requirement(String requirementName, String description, int numberRequiredForCompletion, LinkedList<String> coachingSteps) {
        this();
        this.name = requirementName;
        this.description = description;
        this.numberRequiredForCompletion = numberRequiredForCompletion;
        this.coachingSteps = coachingSteps;
        this.persistentCoachingSteps = new LinkedList<>(coachingSteps);
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
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

    public boolean hasSubRequirement()
    {
        return !this.getSubRequirements().isEmpty();
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

    public LinkedList<String> getCoachingSteps() {
//        LinkedList<String> coachingStepsCopy = new LinkedList<>(coachingSteps);
        return coachingSteps;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean isInProgress) {
        this.isInProgress = isInProgress;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void resetCoachingSteps()
    {
        this.coachingSteps = new LinkedList<>(persistentCoachingSteps);
    }

    public void resetProgress()
    {
        this.completed = false;
        this.isInProgress = false;
        this.numberOfCompleted = 0;
        resetCoachingSteps();
    }
}
