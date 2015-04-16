package edu.emich.honors.emuhonorscollege.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.emich.honors.emuhonorscollege.R;


public class LoginActivity extends ActionBarActivity {

    private Button newUserButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        newUserButton = (Button) findViewById(R.id.login_new_user);
        submitButton = (Button) findViewById(R.id.login_submit);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToNewUserPage(View view)
    {
        startActivity(new Intent(this, NewUserActivity.class));
    }

    public void submitLoginCredentials(View view)
    {
        startActivity(new Intent(this, ChecklistActivity.class));
    }
}
