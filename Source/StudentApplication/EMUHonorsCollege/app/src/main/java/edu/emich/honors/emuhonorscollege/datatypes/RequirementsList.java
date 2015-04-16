package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;

import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class RequirementsList {
    private final HandbookYear handbookYear;
    private final HonorsType honorsType;
    private final ArrayList<Requirement> requirements;

    public RequirementsList(HandbookYear handbookYear, HonorsType honorsType, ArrayList<Requirement> requirements) {
        this.handbookYear = handbookYear;
        this.honorsType = honorsType;
        this.requirements = requirements;
    }

    public HandbookYear getHandbookYear() {
        return handbookYear;
    }

    public HonorsType getHonorsType() {
        return honorsType;
    }

    public boolean isComplete() {
        boolean isCompleteFlag = true;

        for (Requirement requirement : requirements)
        {
            if (!requirement.isCompleted())
            {
                isCompleteFlag = false;
                break;
            }
        }
        return isCompleteFlag;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }
}
