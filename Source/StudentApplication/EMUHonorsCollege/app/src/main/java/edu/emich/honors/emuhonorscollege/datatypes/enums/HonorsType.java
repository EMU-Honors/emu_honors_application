package edu.emich.honors.emuhonorscollege.datatypes.enums;

public enum HonorsType {
    DEPARTMENTAL("Departmental Honors"),
    UNIVERSITY("University Honors"),
    HIGHEST("Highest Honors"),
    FULL("Full Honors");

    private String name;

    private HonorsType(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
