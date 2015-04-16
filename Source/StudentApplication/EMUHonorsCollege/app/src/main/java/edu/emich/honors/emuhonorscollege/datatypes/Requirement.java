package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Requirement{
    private  String name;
    private  String description;
    private ArrayList<Requirement> components;
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
        this.components = new ArrayList<>();
        this.completed = false;
        this.numberOfCompleted = 0;
        this.numberRequiredForCompletion = 1;
        this.hierarchyLevel = 1;
        this.coachingSteps = new LinkedList<>();
        this.persistentCoachingSteps = new LinkedList<>();
    }

    public Requirement(String requirementName,
                       int numberRequiredForCompletion,
                       LinkedList<String> coachingSteps)
    {
        this();
        this.name = requirementName;
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

    public boolean hasComponent()
    {
        return !this.getComponents().isEmpty();
    }

    public ArrayList<Requirement> getComponents() {
        return components;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void addComponent(Requirement componentToAdd)
    {
        componentToAdd.hierarchyLevel++;
        components.add(componentToAdd);
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
