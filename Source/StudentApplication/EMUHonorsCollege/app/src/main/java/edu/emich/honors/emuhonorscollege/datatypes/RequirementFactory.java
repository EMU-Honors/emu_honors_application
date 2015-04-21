package edu.emich.honors.emuhonorscollege.datatypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import edu.emich.honors.emuhonorscollege.connection.DB_Handler;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class RequirementFactory {
    public static RequirementsList createRequirementsList(HandbookYear year, HonorsType type) {
        DB_Handler instance = DB_Handler.getInstance();

        String yearStr = year.toString().split("-")[0];
        String typeStr = type.toString().toLowerCase().split(" ")[0];

        JSONObject handbook = instance.handbookRequest(yearStr, typeStr);

        ArrayList<Requirement> requirementsToAdd = new ArrayList<>();
        String lastJsonRequirement = "";
        int requirementIndex = -1;

        try {
            JSONArray jsonRequirements = new JSONArray(handbook.getString("requirements"));
            for (int i = 0; i < jsonRequirements.length(); i++) {
                JSONObject currentJsonRequirement = jsonRequirements.getJSONObject(i);

                if (!lastJsonRequirement.equals(currentJsonRequirement.getString("requirement_name"))) {
                    lastJsonRequirement = currentJsonRequirement.getString("requirement_name");
                    requirementsToAdd.add(++requirementIndex, new Requirement(lastJsonRequirement, 1, new LinkedList<String>()));
                }
                requirementsToAdd.get(requirementIndex).addComponent(new Requirement(currentJsonRequirement.getString("name"),
                        currentJsonRequirement.getInt("total"),
                        new LinkedList<>(Arrays.asList(currentJsonRequirement.getString("description")))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new RequirementsList(year, type, requirementsToAdd);
    }
}
