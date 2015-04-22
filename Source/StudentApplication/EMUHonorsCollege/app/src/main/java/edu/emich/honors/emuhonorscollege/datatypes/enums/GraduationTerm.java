package edu.emich.honors.emuhonorscollege.datatypes.enums;

public enum GraduationTerm {
    FALL ("Fall"),
    WINTER ("Winter"),
    SUMMER ("Summer");

    private final String name;

    private GraduationTerm(String s)
    {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
