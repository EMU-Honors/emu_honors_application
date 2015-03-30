package edu.emich.honors.emuhonorscollege;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class NewUserActivity extends ActionBarActivity {

    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        submitButton = (Button) findViewById(R.id.new_user_submit);
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

    public void submitClick(View view) {
        Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void displayFullHonorsDialog(View view) {
        CheckBox fullHonorsCheckbox = (CheckBox) findViewById(R.id.fullHonorsCheckBox);
        fullHonorsCheckbox.setChecked(false);

        AlertDialog.Builder fullHonorsDialog = new AlertDialog.Builder(this);
        fullHonorsDialog.setTitle("Full Honors Not Supported");
        fullHonorsDialog.setMessage("Full Honors is not supported by the application at this time. Please contact your Honors Adviser for assistance.");
        fullHonorsDialog.show();
    }
}
