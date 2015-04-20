package edu.emich.honors.emuhonorscollege.datatypes;

import java.io.Serializable;

import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class HonorsHandbook implements Serializable {
    HandbookYear handbookYear;
    RequirementsList departmentalHonorsRequirements;
    RequirementsList universityHonorsRequirements;
    RequirementsList highestHonorsRequirements;

    public HonorsHandbook(HandbookYear handbookYear,
                          RequirementsList departmentalHonorsRequirements,
                          RequirementsList universityHonorsRequirements,
                          RequirementsList highestHonorsRequirements) {
        this.handbookYear = handbookYear;
        this.departmentalHonorsRequirements = departmentalHonorsRequirements;
        this.universityHonorsRequirements = universityHonorsRequirements;
        this.highestHonorsRequirements = highestHonorsRequirements;
    }

    public HonorsHandbook(HandbookYear year, HonorsType type) {
        this.handbookYear = year;
        switch (type) {

            case DEPARTMENTAL:
                this.departmentalHonorsRequirements = RequirementFactory.createRequirementsList(year, type);
                break;
            case UNIVERSITY:
                this.universityHonorsRequirements = RequirementFactory.createRequirementsList(year, type);
                break;
            case HIGHEST:
                this.highestHonorsRequirements = RequirementFactory.createRequirementsList(year, type);
                break;
            case FULL:
                break;
        }
    }

    public static HonorsHandbook getSampleHandbook() {
//        HonorsHandbook sampleHandbook = new HonorsHandbook(HandbookYear.YEAR_2014, RequirementsList.getSampleRequirementsList(), null, null);
        HonorsHandbook sampleHandbook = new HonorsHandbook(HandbookYear.YEAR_2014,
                RequirementFactory.createRequirementsList(HandbookYear.YEAR_2007, HonorsType.UNIVERSITY),
                null,
                null);
        return sampleHandbook;
    }

    public HandbookYear getHandbookYear() {
        return handbookYear;
    }

    public RequirementsList getDepartmentalHonorsRequirements() {
        return departmentalHonorsRequirements;
    }

    public RequirementsList getUniversityHonorsRequirements() {
        return universityHonorsRequirements;
    }

    public RequirementsList getHighestHonorsRequirements() {
        return highestHonorsRequirements;
    }

    public RequirementsList getRequirementsList(HonorsType honorsType) {
        RequirementsList requirementsListToReturn = null;

        if (honorsType == HonorsType.UNIVERSITY) {
            requirementsListToReturn = getUniversityHonorsRequirements();
        } else if (honorsType == HonorsType.DEPARTMENTAL) {
            requirementsListToReturn = getDepartmentalHonorsRequirements();
        } else if (honorsType == HonorsType.HIGHEST) {
            requirementsListToReturn = getHighestHonorsRequirements();
        }

        return requirementsListToReturn;
    }

}
