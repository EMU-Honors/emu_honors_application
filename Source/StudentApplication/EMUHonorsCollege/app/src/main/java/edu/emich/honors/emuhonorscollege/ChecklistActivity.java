package edu.emich.honors.emuhonorscollege;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ChecklistActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
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

    public void onCheck(View view)
    {
        AlertDialog.Builder coachingAlert = new AlertDialog.Builder(this);

        final CheckBox currentCheckBox = (CheckBox) view; //Check box gets marked at the start of this action.
        if (!currentCheckBox.isChecked()) {
            coachingAlert.setMessage("Are you sure you'd like to mark this requirement as incomplete?");

            coachingAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Requirement marked as incomplete.", Toast.LENGTH_SHORT).show();
                    currentCheckBox.setChecked(false);
                }
            });

            coachingAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "No change made.", Toast.LENGTH_SHORT).show();
                    currentCheckBox.setChecked(true);
                }
            });
        }
        else {
            coachingAlert.setMessage("Have you completed all the steps necessary for this requirement?");

            coachingAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Requirement completed!", Toast.LENGTH_SHORT).show();
                    currentCheckBox.setChecked(true);
                }
            });

            coachingAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Requirement still needs completion.", Toast.LENGTH_SHORT).show();
                    currentCheckBox.setChecked(false);
                }
            });
        }

        coachingAlert.show();
    }
}
