package edu.emich.honors.emuhonorscollege.datatypes;

import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;

public class HonorsHandbook {
    HandbookYear handbookYear;
    RequirementsList departmentalHonorsRequirements;
    RequirementsList universityHonorsRequirements;
    RequirementsList highestHonorsRequirements;

    public HonorsHandbook(HandbookYear handbookYear,
                          RequirementsList departmentalHonorsRequirements,
                          RequirementsList universityHonorsRequirements,
                          RequirementsList highestHonorsRequirements)
    {
        this.handbookYear = handbookYear;
        this.departmentalHonorsRequirements = departmentalHonorsRequirements;
        this.universityHonorsRequirements = universityHonorsRequirements;
        this.highestHonorsRequirements = highestHonorsRequirements;
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
}
