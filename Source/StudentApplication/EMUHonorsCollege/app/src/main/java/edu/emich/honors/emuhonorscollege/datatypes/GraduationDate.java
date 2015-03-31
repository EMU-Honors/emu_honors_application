package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;
import java.util.Calendar;

import edu.emich.honors.emuhonorscollege.datatypes.enums.GraduationTerm;

public class GraduationDate {
    private GraduationTerm term;
    private String year;
    private static final int numberOfYearsToShow = 6;

    public GraduationDate(GraduationTerm term, String year)
    {
        this.term = term;
        this.year = year;
    }

    public static ArrayList<String> getListOfCurrentGraduationYears()
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int maxYearToShow = currentYear + numberOfYearsToShow;
        ArrayList<String> currentGraduationYears = new ArrayList<String>();

        for (int yearToAdd = currentYear; yearToAdd <= maxYearToShow; yearToAdd++)
        {
                currentGraduationYears.add(String.valueOf(yearToAdd));
        }

        return currentGraduationYears;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public GraduationTerm getTerm() {
        return term;
    }

    public void setTerm(GraduationTerm term) {
        this.term = term;
    }
}
