package edu.emich.honors.emuhonorscollege.datatypes;

import java.io.Serializable;

import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class HonorsHandbook  implements Serializable {
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

    public static HonorsHandbook getSampleHandbook()
    {
        HonorsHandbook sampleHandbook = new HonorsHandbook(HandbookYear.YEAR_2014, RequirementsList.getSampleRequirementsList(), null, null);
        return sampleHandbook;
    }

    public RequirementsList getRequirementsList(HonorsType honorsType)
    {
        RequirementsList requirementsListToReturn = null;

        if (honorsType == HonorsType.UNIVERSITY)
        {
            requirementsListToReturn = getUniversityHonorsRequirements();
        }
        else if (honorsType == HonorsType.DEPARTMENTAL)
        {
            requirementsListToReturn = getDepartmentalHonorsRequirements();
        }
        else if (honorsType == HonorsType.HIGHEST)
        {
            requirementsListToReturn = getHighestHonorsRequirements();
        }

        return requirementsListToReturn;
    }

}
