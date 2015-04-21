package edu.emich.honors.emuhonorscollege.activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.datatypes.Requirement;
import edu.emich.honors.emuhonorscollege.datatypes.RequirementsList;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;


public class InProgressActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mLinearLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private ArrayList<Requirement> mRequirements;
    // private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_progress);


        //Menu Setup
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Checklist Setup
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.checklist_linear_layout);

        //        Placeholder for a real list of requirements pulled from the DB
        ArrayList<Requirement> tempListOfRequirements = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            LinkedList<String> dummyCoachingSteps = new LinkedList<String>();
            dummyCoachingSteps.add("Did you put your left foot in?");
            dummyCoachingSteps.add("Did you put your left foot out?");
            dummyCoachingSteps.add("Did you put your left foot in and shake it all about?");

            Requirement tempComponent = new Requirement("Requirement " + i, 3, dummyCoachingSteps);
            if (i % 2 == 0) {
                tempComponent.setNumberOfCompleted(3);
            } else {
                tempComponent.addComponent(new Requirement());
                tempComponent.setInProgress(true);
            }
            tempListOfRequirements.add(tempComponent);
        }
        RequirementsList requirementsList = new RequirementsList(HandbookYear.YEAR_2014, HonorsType.UNIVERSITY, tempListOfRequirements);


        buildCheckList(tempListOfRequirements, parentLayout);
    }

    public void buildCheckList(ArrayList<Requirement> listOfRequirements, LinearLayout parentLayout) {
        for (final Requirement requirement : listOfRequirements) {
            LinearLayout requirementRow = new LinearLayout(this);
            requirementRow.setOrientation(LinearLayout.HORIZONTAL);
            requirementRow.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            CheckBox requirementCheckbox = new CheckBox(this);
            requirementCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheck((CheckBox) v, requirement);
                }
            });

            if (!requirement.isInProgress()) {
                continue;
            } else {

                requirementCheckbox.setLayoutParams(new ActionBar.LayoutParams(75, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
                requirementCheckbox.setHeight(ActionBar.LayoutParams.MATCH_PARENT);
                requirementRow.addView(requirementCheckbox);

                final ImageView dropDownArrow = new ImageView(this);
                dropDownArrow.setImageResource(R.drawable.arrow_dropdown);
                dropDownArrow.setLayoutParams(new ActionBar.LayoutParams(125, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
                dropDownArrow.setPadding(25, 0, 25, 0);
                dropDownArrow.setRotation(-90);
                dropDownArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dropDownArrow.getRotation() == -90) {
                            dropDownArrow.setRotation(0);
                        } else {
                            dropDownArrow.setRotation(-90);
                        }
                        Toast.makeText(getApplicationContext(), "Drop down!", Toast.LENGTH_SHORT).show();
                    }
                });

                if (requirement.hasComponent()) {
                    dropDownArrow.setVisibility(View.VISIBLE);
                } else {
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


    public void onCheck(final CheckBox checkbox, final Requirement requirement) {
        if (checkbox.isChecked()) {
            displayCoachingStep(requirement, checkbox);
        } else  // The checkbox was already checked before!
        {
            AlertDialog.Builder markRequirementIncompleteAlert = new AlertDialog.Builder(this);
            markRequirementIncompleteAlert.setMessage("Are you sure you'd like to mark this requirement as incomplete? You will lose all recorded progress for this requirement.");

            markRequirementIncompleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requirement.resetProgress();

                    Toast.makeText(getApplicationContext(), "Requirement marked as incomplete.", Toast.LENGTH_SHORT).show();
                    checkbox.setChecked(false);

                    // Write to Database Here
                }
            });

            markRequirementIncompleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "No change made.", Toast.LENGTH_SHORT).show();
                    checkbox.setChecked(true);
                }
            });

            markRequirementIncompleteAlert.show();
        }
    }

    private void displayCoachingStep(final Requirement requirement, final CheckBox currentCheckBox) {
        LinkedList<String> coachingSteps = requirement.getCoachingSteps();
        String coachingStep = coachingSteps.peek();

        final boolean isLastCoachingStep;
        if (coachingSteps.peekLast().equals(coachingStep)) {
            isLastCoachingStep = true;
        } else {
            isLastCoachingStep = false;
        }

        AlertDialog.Builder coachingAlert = new AlertDialog.Builder(this);
        CoachingStepPositiveListener coachingStepPositiveListener = new CoachingStepPositiveListener(isLastCoachingStep, requirement, currentCheckBox);
        CoachingStepNegativeListener coachingStepNegativeListener = new CoachingStepNegativeListener(currentCheckBox);

        coachingAlert.setMessage(coachingStep);
        coachingAlert.setPositiveButton("Yes", coachingStepPositiveListener);
        coachingAlert.setNegativeButton("No", coachingStepNegativeListener);

        coachingAlert.show();
    }

    private void addDrawerItems() {
        String[] osArray = {"Settings", "Logout", "Checklist"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent a = new Intent(InProgressActivity.this, SettingsActivity.class);
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(InProgressActivity.this, LoginActivity.class);
                        startActivity(b);
                        break;
                    case 2:
                        Intent c = new Intent(InProgressActivity.this, ChecklistActivity.class);
                        startActivity(c);
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
                getSupportActionBar().setTitle("Navigation");
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

    private class CoachingStepPositiveListener implements DialogInterface.OnClickListener {
        private boolean isLastCoachingStep;
        private CheckBox checkbox;
        private Requirement requirement;
        private LinkedList<String> coachingSteps;

        public CoachingStepPositiveListener(boolean isLastCoachingStep, Requirement requirement, CheckBox checkbox) {
            this.isLastCoachingStep = isLastCoachingStep;
            this.checkbox = checkbox;
            this.requirement = requirement;
            this.coachingSteps = requirement.getCoachingSteps();
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            if (isLastCoachingStep) {
                Toast.makeText(getApplicationContext(), "Requirement completed!", Toast.LENGTH_SHORT).show();
                checkbox.setChecked(true);
                requirement.setInProgress(false);
                requirement.setCompleted(true);
                coachingSteps.removeFirst();
                // Write to Database Here
            } else {
                coachingSteps.removeFirst();
                requirement.setInProgress(true);
                displayCoachingStep(requirement, checkbox);
            }
        }
    }

    private class CoachingStepNegativeListener implements DialogInterface.OnClickListener {
        private CheckBox checkbox;

        public CoachingStepNegativeListener(CheckBox checkbox) {
            this.checkbox = checkbox;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            Toast.makeText(getApplicationContext(), "Requirement still needs completion.", Toast.LENGTH_SHORT).show();
            checkbox.setChecked(false);
        }
    }
}
