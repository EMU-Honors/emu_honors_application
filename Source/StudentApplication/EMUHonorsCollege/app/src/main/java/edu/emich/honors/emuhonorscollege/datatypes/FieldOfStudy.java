package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;
import java.util.Arrays;

import edu.emich.honors.emuhonorscollege.datatypes.enums.AcademicProgram;

public class FieldOfStudy {
    private ArrayList<AcademicProgram> majors;
    private ArrayList<AcademicProgram> minors;

    public FieldOfStudy(ArrayList<AcademicProgram> majors, ArrayList<AcademicProgram> minors) {
        this.majors = majors;
        this.minors = minors;
    }

    public FieldOfStudy(AcademicProgram major, AcademicProgram minor) {
        this(new ArrayList<AcademicProgram>(Arrays.asList(major)), new ArrayList<AcademicProgram>(Arrays.asList(minor)));
    }

    public ArrayList<AcademicProgram> getMajors() {
        return majors;
    }

    public void setMajors(ArrayList<AcademicProgram> majors) {
        this.majors = majors;
    }

    public ArrayList<AcademicProgram> getMinors() {
        return minors;
    }

    public void setMinors(ArrayList<AcademicProgram> minors) {
        this.minors = minors;
    }
}
