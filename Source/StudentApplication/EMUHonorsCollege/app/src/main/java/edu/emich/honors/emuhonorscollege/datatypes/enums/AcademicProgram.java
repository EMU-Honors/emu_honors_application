package edu.emich.honors.emuhonorscollege.datatypes.enums;

/**
 * This class may not be necessary, but acts as a placeholder until database connections are set up.
 */
public enum AcademicProgram {
    NONE("None"),
    MATH("Mathematics"),
    COMPUTER_SCIENCE("Computer Science"),
    HISTORY("History");

    private String name;

    private AcademicProgram(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
