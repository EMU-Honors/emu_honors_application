package edu.emich.honors.emuhonorscollege.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.emich.honors.emuhonorscollege.HonorsApplication;
import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.connection.DB_Handler;
import edu.emich.honors.emuhonorscollege.datatypes.User;


public class LoginActivity extends ActionBarActivity {

    private TextView userNameField;
    private TextView userPassField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        DB_Handler.getInstance(getApplicationContext());

        this.userNameField = (TextView) findViewById(R.id.login_email);
        this.userPassField = (TextView) findViewById(R.id.login_password);
    }


    public void goToNewUserPage(View view) {
        startActivity(new Intent(this, NewUserActivity.class));
    }

    public void submitLoginCredentials(View view) {
        DB_Handler instance = DB_Handler.getInstance(getApplicationContext());
        String username = this.userNameField.getText().toString().toLowerCase();
        String password = this.userPassField.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject login = instance.login(username, password);

            if (login == null) {
                // Failed to connect
                Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
            } else {

                String response = "fail";
                try {
                    if (login.getString("response").equals("fail")) {
                        Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("hi", login.getString("response"));
                        response = login.getString("response");
                        if (response.equals("success")) {
                            Log.d("name", login.getString("content"));
                        }
                    }
                } catch (JSONException e) {
//                e.printStackTrace();
                }

                if (response.equals("success")) {
                    ((HonorsApplication) this.getApplication()).setCurrentUser(User.getSampleUser());
                    startActivity(new Intent(this, ChecklistActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
