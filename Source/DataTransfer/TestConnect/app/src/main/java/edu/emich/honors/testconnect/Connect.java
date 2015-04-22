package edu.emich.honors.testconnect;


import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Connect extends ActionBarActivity {

    Button theButton;
    TextView theOutput;
<<<<<<< HEAD
    DB_Handler the_dbs;
    private SQLiteHelper localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        this.the_dbs = new DB_Handler(getApplicationContext());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.theButton = (Button) findViewById(R.id.theTestButton);
        this.theOutput = (TextView) findViewById(R.id.outputTextView);

        this.theButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"hi",Toast.LENGTH_LONG).show();
                //theOutput.append(" clicked");
                //Toast.makeText(getBaseContext(), doASomething(), Toast.LENGTH_LONG).show();

//                String result = doASomething();
                //result = "{ requirements:" + result + "}";  //JSONarray -> JSONobject
                //String afterParse = parseJSON(result);
                //theOutput.setText(result);  //display results after parsing JSON


               theOutput.setText((CharSequence) the_dbs.handbookRequest("2007","university").toString());
=======
    private SQLiteHelper localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.theButton = (Button) findViewById(R.id.theTestButton);
        this.theOutput = (TextView) findViewById(R.id.outputTextView);

        this.theButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"hi",Toast.LENGTH_LONG).show();
                //theOutput.append(" clicked");
                //Toast.makeText(getBaseContext(), doASomething(), Toast.LENGTH_LONG).show();
                String result = doASomething();
                result = "{ requirements:" + result + "}";  //JSONarray -> JSONobject
                String afterParse = parseJSON(result);
                theOutput.setText(afterParse);  //display results after parsing JSON
>>>>>>> refs/heads/Android
            }

        });


    }

    private String doASomething() {
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://db2.emich.edu/~201501_cosc481_group01/android_test.php");

        JSONObject object = new JSONObject();
        try {
            object.put("handbook_year",2007);
            object.put("honors_type","departmental");
            List dict = new ArrayList();

            dict.add(new BasicNameValuePair("get_handbook", object.toString()));
            post.setEntity(new UrlEncodedFormEntity(dict));

            HttpResponse response = client.execute(post);

            result += EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String parseJSON(String result) {

        JSONObject jsonResponse;
        String outputData = "";

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONObject(result);

            /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
            /*******  Returns null otherwise.  *******/
            JSONArray yearAndType = jsonResponse.optJSONArray("requirements");

            /*********** Process each JSON Node ************/

            int lengthJsonArr = yearAndType.length();


            for(int i=0; i < lengthJsonArr; i++)
            {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = yearAndType.getJSONObject(i);

                /******* Fetch node values **********/
                int dispNumber = Integer.parseInt(jsonChildNode.optString("display_number").toString());
                String name = jsonChildNode.optString("requirement_name").toString();
                String component = jsonChildNode.optString("name").toString();
                int totalNeeded = Integer.parseInt(jsonChildNode.optString("total").toString());
                String description = jsonChildNode.optString("description").toString();

                localDB = new SQLiteHelper(this);
                localDB.insertRequirement("2007", "deparmental", dispNumber, name, component, totalNeeded, description);

            /*  ouptputData is only the strings that I parsed from JSON
              * I only did this to make sure I parsed the JSON correclty
               * The output you see on the screen is NOT from the database */
                outputData += "REQMT: \n" +dispNumber+ "\n"
                                +name+ "\n"
                                +component+ "\n"
                                +totalNeeded+ "\n"
                                +description+ "\n********************************** \n" ;
            }


        } catch (JSONException e) {

            e.printStackTrace();
        }

        return outputData;  //print dat shit

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
