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
    private Requirement parentRequirement;

    public Requirement() {
        this.name = "";
        this.description = "";
        this.components = new ArrayList<>();
        this.completed = false;
        this.numberOfCompleted = 0;
        this.numberRequiredForCompletion = 1;
        this.hierarchyLevel = 0;
        this.coachingSteps = new LinkedList<>();
        this.persistentCoachingSteps = new LinkedList<>();
        this.parentRequirement = null;
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
        boolean isCompleteFlag = true;

        for (Requirement component : components)
        {
            if (!component.isCompleted())
            {
                isCompleteFlag = false;
                break;
            }
        }

        return isCompleteFlag;
    }

    public void addComponent(Requirement componentToAdd)
    {
        componentToAdd.setParentRequirement(this);
        componentToAdd.hierarchyLevel++;
        components.add(componentToAdd);
    }

    private void setParentRequirement(Requirement parentRequirement) {
        this.parentRequirement = parentRequirement;
    }

    public LinkedList<String> getCoachingSteps() {
//        LinkedList<String> coachingStepsCopy = new LinkedList<>(coachingSteps);
        return coachingSteps;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public boolean isInProgress() {
        boolean isInProgressFlag = true;

        for (Requirement component : components)
        {
            if (!component.isCompleted())
            {
                isInProgressFlag = false;
                break;
            }
        }
        return isInProgressFlag;
    }

    public boolean hasParentRequirement()
    {
        return parentRequirement != null;
    }

    public Requirement getParentRequirement()
    {
        return parentRequirement;
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
