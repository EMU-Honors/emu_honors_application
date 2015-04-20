package edu.emich.honors.emuhonorscollege.connection;

import android.content.Context;
import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 4/15/2015.
 */
public class DB_Handler {

    private static DB_Handler instance;
    private final String DESTINATION = "http://db2.emich.edu/~201501_cosc481_group01/other/ajax_handler.php";
    private final HttpClient client;
    private final Context context;

    //constructor
    private DB_Handler(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.context = context;
        this.client = new DefaultHttpClient();
    }

    public static DB_Handler getInstance() {
        if (DB_Handler.instance == null) {
            System.exit(1);
        }
        return instance;
    }

    public static DB_Handler getInstance(Context context) {
        if (DB_Handler.instance == null) {
            instance = new DB_Handler(context);
        }
        return instance;
    }

    //method every time we need to send to remote DB
//many methods below are using sendToRemote()
    private JSONObject sendToRemote(String request, JSONObject obj) {
        String result = "";
        HttpPost post = new HttpPost(DESTINATION);
        try {

            List dict = new ArrayList();

            dict.add(new BasicNameValuePair("content", obj.toString()));
            dict.add(new BasicNameValuePair("request", request));
            post.setEntity(new UrlEncodedFormEntity(dict));

            HttpResponse response = client.execute(post);

            result += EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public JSONObject login(String user_name, String password) {
        JSONObject object = new JSONObject();
        try {
            object.put("username", user_name);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendToRemote("login", object);
    }


    //public JSONObject newUser(JSONObject user_data)
    public JSONObject newUser(JSONObject userData) {
        return sendToRemote("new_user", userData);
    }


    //public JSONObject update_user(JSONObject user_data)


    //insert into table
    public boolean addLocalHandbook(JSONObject handbook) {

        boolean isFinished = false;

        try {
            JSONArray yearAndType = handbook.optJSONArray("requirements");

            /*********** Process each JSON Node ************/
            int lengthJsonArr = yearAndType.length();

            for (int i = 0; i < lengthJsonArr; i++) {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = yearAndType.getJSONObject(i);

                /******* Fetch node values **********/
                int dispNumber = Integer.parseInt(jsonChildNode.optString("display_number"));
                String name = jsonChildNode.optString("requirement_name");
                String component = jsonChildNode.optString("name");
                int totalNeeded = Integer.parseInt(jsonChildNode.optString("total"));
                String description = jsonChildNode.optString("description");

                SQLiteHelper localDB = new SQLiteHelper(this.context);
                localDB.insertRequirement("2007", "departmental", dispNumber, name, component, totalNeeded, description);

                isFinished = true;
            }
        } catch (JSONException e) {

            e.printStackTrace();
            //isFinished = false; -> already false
        }

        return isFinished;
    }

    //check if we have using SQLite query
    public boolean isLocalHandbook(String handbook_year, String honors_type) {
        JSONObject object = new JSONObject();
        try {
            object.put("handbook_year", handbook_year);
            object.put("honors_type", honors_type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;  //true if handbook of this year and type exists
    }


    public JSONObject handbookRequest(String year, String type) {
        JSONObject object = new JSONObject();
        try {
            object.put("year", year);
            object.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendToRemote("handbook", object);
    }


    //create
}
