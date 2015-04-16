package edu.emich.honors.emuhonorscollege.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.datatypes.Requirement;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class ChecklistActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

//        Menu Setup
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


//        Checklist Setup
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.checklist_linear_layout);

//        Placeholder for a real list of requirements pulled from the DB
        ArrayList<Requirement> listOfRequirements = new ArrayList<>();
        for (int i = 0; i < 8; i++)
        {
            Requirement tempRequirement = new Requirement("Requirement " + i, "Description goes here!", 3 );
            if (i % 2 == 0)
            {
                tempRequirement.setNumberOfCompleted(3);
            }
            else
            {
                tempRequirement.addSubRequirement(new Requirement());
            }
            listOfRequirements.add(tempRequirement);
        }

        buildCheckList(listOfRequirements, parentLayout);


    }


    public void buildCheckList(ArrayList<Requirement> listOfRequirements, LinearLayout parentLayout)
    {
        for (final Requirement requirement : listOfRequirements)
        {
            LinearLayout requirementRow = new LinearLayout(this);
            requirementRow.setOrientation(LinearLayout.HORIZONTAL);
            requirementRow.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            CheckBox requirementCheckbox = new CheckBox(this);
            requirementCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheck(v);
                }
            });
            requirementCheckbox.setChecked(requirement.isCompleted());
            requirementCheckbox.setLayoutParams(new ActionBar.LayoutParams(75, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
            requirementCheckbox.setHeight(ActionBar.LayoutParams.MATCH_PARENT);
            requirementRow.addView(requirementCheckbox);


            ImageView dropDownArrow = new ImageView(this);
            dropDownArrow.setImageResource(R.drawable.arrow_dropdown);
            dropDownArrow.setLayoutParams(new ActionBar.LayoutParams(125, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
            dropDownArrow.setPadding(25,0,25,0);
            dropDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Drop down!", Toast.LENGTH_SHORT).show();
                }
            });

            if (requirement.hasSubRequirement())
            {
                dropDownArrow.setVisibility(View.VISIBLE);
            }
            else
            {
                dropDownArrow.setVisibility(View.INVISIBLE);
            }
            requirementRow.addView(dropDownArrow);




            TextView requirementTitle = new TextView(this);
            requirementTitle.setText(requirement.getName());
            requirementTitle.setTextSize(34);
            final AlertDialog.Builder descriptionDialogBuilder = new AlertDialog.Builder(this);
            descriptionDialogBuilder.setTitle(requirement.getName());
            descriptionDialogBuilder.setMessage(requirement.getDescription());
            descriptionDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            requirementTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    descriptionDialogBuilder.show();
                    return false;
                }
            });





            requirementRow.addView(requirementTitle);

            parentLayout.addView(requirementRow);
        }
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
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

    private void addDrawerItems() {
        String[] osArray = { "Settings", "Back to Login" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent a = new Intent(ChecklistActivity.this, SettingsActivity.class);
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(ChecklistActivity.this, LoginActivity.class);
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
