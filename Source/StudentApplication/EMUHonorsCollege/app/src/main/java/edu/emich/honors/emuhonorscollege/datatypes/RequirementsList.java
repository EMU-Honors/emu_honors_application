package edu.emich.honors.emuhonorscollege.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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


    public static RequirementsList getSampleRequirementsList()
    {
        ArrayList<Requirement> sampleRequirements = new ArrayList<>();

        sampleRequirements.add(new Requirement("Coursework", 12, new LinkedList<>(Arrays.asList("Did you complete a 3 credit hour Honors course with a grade of B- or better?"))));

        Requirement workshopsRequirement = new Requirement("Workshops", 2, new LinkedList<String>());
        workshopsRequirement.addComponent(new Requirement("Honors Undergraduate Research Workshop", 1, new LinkedList<>(Arrays.asList("Have you attended an Undergraduate Research Workshop?"))));
        workshopsRequirement.addComponent(new Requirement("Departmental Honors Workshop", 1, new LinkedList<>(Arrays.asList("Have you attended an Undergraduate Research Workshop?"))));
        sampleRequirements.add(workshopsRequirement);

        Requirement seniorThesisRequirement = new Requirement("Senior Project", 1, new LinkedList<String>());
        seniorThesisRequirement.addComponent(new Requirement("Senior Thesis Proposal", 1, new LinkedList<>(Arrays.asList("Have you submitted your proposal to the Honors Director and received approval?"))));
        seniorThesisRequirement.addComponent(new Requirement("Senior Project", 1, new LinkedList<String>(Arrays.asList("Have you completed your senior thesis/project, " +
                "received approval and signatures from your supervising instructor, Honors Advisor, " +
                "and Department Head? You must also deliver three copies each of the completed thesis " +
                "and signed signature pages, one electronic copy of the thesis, a Library Release form, " +
                "and one copy of your graded Honors Contract for your Senior Thesis Project to the Honors office."))));
        sampleRequirements.add(seniorThesisRequirement);

        return new RequirementsList(HandbookYear.YEAR_2014, HonorsType.DEPARTMENTAL, sampleRequirements);
    }

}
