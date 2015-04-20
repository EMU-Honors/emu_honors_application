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

/**
 * Created by eddie on 4/20/15.
 */
public class RequirementFactory {
    public static RequirementsList createRequirementsList(HandbookYear year, HonorsType type) {
        DB_Handler instance = DB_Handler.getInstance();

        String yearStr = year.toString().split("-")[0];
        String typeStr = type.toString().toLowerCase().split(" ")[0];

        JSONObject handbook = instance.handbookRequest(yearStr, typeStr);
        RequirementsList toReturn = null;
        ArrayList<Requirement> reqs = new ArrayList<>();
        String lastReq = "";
        int reqPointer = -1;
        try {
            JSONArray requirements = new JSONArray(handbook.getString("requirements"));
            for (int i = 0; i < requirements.length(); i++) {
                JSONObject curReq = requirements.getJSONObject(i);
                if (!lastReq.equals(curReq.getString("requirement_name"))) {
                    lastReq = curReq.getString("requirement_name");
                    reqs.add(++reqPointer, new Requirement(lastReq, 1, new LinkedList<String>()));
                }
                reqs.get(reqPointer).addComponent(new Requirement(curReq.getString("name"),
                        curReq.getInt("total"),
                        new LinkedList<>(Arrays.asList(curReq.getString("description")))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        toReturn = new RequirementsList(year, type, reqs);
        return toReturn;
    }
}
