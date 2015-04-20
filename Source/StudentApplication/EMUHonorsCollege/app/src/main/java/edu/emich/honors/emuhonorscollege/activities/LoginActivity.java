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

    private Button newUserButton;
    private Button submitButton;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private TextView userNameField;
    private TextView userPassField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        DB_Handler.getInstance(getApplicationContext());

        newUserButton = (Button) findViewById(R.id.login_new_user);
        submitButton = (Button) findViewById(R.id.login_submit);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        this.userNameField = (TextView) findViewById(R.id.login_email);
        this.userPassField = (TextView) findViewById(R.id.login_password);

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNewUserPage(View view) {
        startActivity(new Intent(this, NewUserActivity.class));
    }

    public void submitLoginCredentials(View view) {
        DB_Handler instance = DB_Handler.getInstance(getApplicationContext());
        JSONObject login = instance.login(this.userNameField.getText().toString(), this.userPassField.getText().toString());
        String response = "fail";
        try {
            Log.d("hi", login.getString("response"));
            response = login.getString("response");
            if (response.equals("success")) {
                Log.d("name", login.getString("content"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (response.equals("success")) {
            ((HonorsApplication) this.getApplication()).setCurrentUser(User.getSampleUser());
            startActivity(new Intent(this, ChecklistActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username/Password!", Toast.LENGTH_SHORT).show();
        }

    }

    private void addDrawerItems() {
        String[] osArray = {"Settings", "Checklist"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent a = new Intent(LoginActivity.this, SettingsActivity.class);
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(LoginActivity.this, ChecklistActivity.class);
                        startActivity(b);
                        break;
                    default:
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
