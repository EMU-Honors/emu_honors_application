package edu.emich.honors.emuhonorscollege;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class ChecklistActivity extends ActionBarActivity {

    private CheckBox honorsCreditsCheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        honorsCreditsCheckbox = (CheckBox) findViewById(R.id.checklist_honors_credits);
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
        CheckBox currentCheckBox = (CheckBox) view;
        if (currentCheckBox.isChecked()) {
            Toast.makeText(this, "Can't do that. Too bad.", Toast.LENGTH_SHORT).show();
            currentCheckBox.setChecked(false);
        }
    }
}
