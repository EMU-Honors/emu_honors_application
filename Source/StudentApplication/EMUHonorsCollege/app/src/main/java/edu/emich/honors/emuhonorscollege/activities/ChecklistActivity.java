package edu.emich.honors.emuhonorscollege.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.datatypes.Requirement;
import edu.emich.honors.emuhonorscollege.datatypes.RequirementsList;

public class ChecklistActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private final String COMPLETED_GREEN = "#669900";
    private final String IN_PROGRESS_YELLOW = "#FFBB33";
    private int indentationSize = 100;
    private final int COLLAPSED = -90;
    private final int EXPANDED = 0;

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
        RequirementsList requirementsList = RequirementsList.getSampleRequirementsList();

        TextView honorsTypeTitle = new TextView(this);
        honorsTypeTitle.setText(requirementsList.getHonorsType().toString());
        honorsTypeTitle.setTextSize(40);
        honorsTypeTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        honorsTypeTitle.setTextColor(Color.BLACK);

        parentLayout.addView(honorsTypeTitle);

        buildCheckList(requirementsList.getRequirements(), parentLayout);
    }



    public void buildCheckList(ArrayList<Requirement> listOfRequirements, LinearLayout parentLayout)
    {
        for (final Requirement requirement : listOfRequirements)
        {
            buildRequirementRow(requirement, parentLayout);
        }
    }

    private void buildRequirementRow(final Requirement requirement, LinearLayout parentLayout) {
        if (requirement.hasComponent())
        {
            LinearLayout requirementBlock = new LinearLayout(this);
            requirementBlock.setOrientation(LinearLayout.VERTICAL);
            requirementBlock.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            parentLayout.addView(requirementBlock);
            parentLayout = requirementBlock;
        }

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
        requirementCheckbox.setChecked(requirement.isCompleted());
        requirementCheckbox.setLayoutParams(new ActionBar.LayoutParams(75, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        requirementCheckbox.setHeight(ActionBar.LayoutParams.MATCH_PARENT);

        if (requirement.getHierarchyLevel() == 0)
        {
            requirementCheckbox.setClickable(false);
        }
        else {
            requirementRow.setVisibility(View.GONE);
            for (int numberOfPaddingBlocks = 0; numberOfPaddingBlocks < requirement.getHierarchyLevel(); numberOfPaddingBlocks++) {
                Space space = new Space(this);
                space.setMinimumWidth(indentationSize);
                requirementRow.addView(space);
            }
        }

        requirementRow.addView(requirementCheckbox);

        final ImageView dropDownArrow = new ImageView(this);
        dropDownArrow.setImageResource(R.drawable.arrow_dropdown);
        dropDownArrow.setLayoutParams(new ActionBar.LayoutParams(125, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        dropDownArrow.setPadding(25, 0, 25, 0);
        dropDownArrow.setRotation(COLLAPSED);
        dropDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropDownArrow.getRotation() == COLLAPSED)
                {
                    dropDownArrow.setRotation(EXPANDED);
                }
                else
                {
                    dropDownArrow.setRotation(COLLAPSED);
                }
                toggleComponentDropDown(v);
            }
        });

        if (requirement.hasComponent())
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

        if (requirement.getHierarchyLevel() == 0)  // Heading Requirement
        {
            requirementTitle.setTextSize(26);
        }
        else {  // Component
            requirementTitle.setTextSize(18);
        }

        if (requirement.isCompleted())
        {
            requirementTitle.setTextColor(Color.parseColor(COMPLETED_GREEN));
        }
        else if (requirement.isInProgress())
        {
            requirementTitle.setTextColor(Color.parseColor(IN_PROGRESS_YELLOW));
        }
        else
        {
            requirementTitle.setTextColor(Color.BLACK);
        }

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

        for (Requirement component : requirement.getComponents())
        {
            buildRequirementRow(component, parentLayout);
        }
    }

    private void toggleComponentDropDown(View view)
    {
        ViewParent requirementRow = view.getParent();
        ViewGroup requirementBlock = (ViewGroup) requirementRow.getParent();

        for (int rowIndex = 1; rowIndex < requirementBlock.getChildCount(); rowIndex++)
        {
            View componentRow = requirementBlock.getChildAt(rowIndex);
            if (view.getRotation() == COLLAPSED)
                componentRow.setVisibility(View.GONE);
            else
                componentRow.setVisibility(View.VISIBLE);
        }

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

    public void onCheck(final CheckBox checkbox, final Requirement requirement)
    {
        if (checkbox.isChecked()){
            displayCoachingStep(requirement, checkbox);
        }
        else  // The checkbox was already checked before!
        {
            AlertDialog.Builder markRequirementIncompleteAlert = new AlertDialog.Builder(this);
            markRequirementIncompleteAlert.setMessage("Are you sure you'd like to mark this requirement as incomplete? You will lose all recorded progress for this requirement.");

            markRequirementIncompleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requirement.resetProgress();
                    checkbox.setChecked(false);

                    ViewGroup row = (ViewGroup) checkbox.getParent();

                    for (int viewIndex = 0; viewIndex < row.getChildCount(); viewIndex++)
                    {
                        View view = row.getChildAt(viewIndex);
                        if (view instanceof TextView)
                        {
                            ((TextView) view).setTextColor(Color.BLACK);
                        }
                    }

                    updateRequirementTextColor(checkbox, requirement);
                    // Write to Database Here
                }
            });

            markRequirementIncompleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkbox.setChecked(true);
                }
            });

            markRequirementIncompleteAlert.show();
        }
    }

    private class CoachingStepPositiveListener implements DialogInterface.OnClickListener
    {
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
                checkbox.setChecked(true);
                requirement.setInProgress(false);
                requirement.setCompleted(true);
                coachingSteps.removeFirst();

                updateRequirementTextColor(checkbox, requirement);
                // Write to Database Here
            }
            else
            {
                coachingSteps.removeFirst();
                requirement.setInProgress(true);

                displayCoachingStep(requirement, checkbox);
            }
        }
    }

    private void updateRequirementTextColor(CheckBox checkbox, Requirement requirement) {
        ViewGroup row = (ViewGroup) checkbox.getParent();
        TextView requirementTextView = null;

        for (int viewIndex = 0; viewIndex < row.getChildCount(); viewIndex++)
        {
            View view = row.getChildAt(viewIndex);
            if (view instanceof TextView && !(view instanceof CheckBox))
            {
                requirementTextView = (TextView) view;
                break;
            }
        }

        if (checkbox.isChecked())
        {
            requirementTextView.setTextColor(Color.parseColor(COMPLETED_GREEN));

        }
        else
        {
            requirementTextView.setTextColor(Color.BLACK);
        }

        ViewGroup block = (ViewGroup) row.getParent();
        TextView parentRequirementTextView = null;
        CheckBox parentRequirementCheckBox = null;
        for (int viewIndex = 0; viewIndex < block.getChildCount(); viewIndex++)
        {
            View view = block.getChildAt(viewIndex);
            if (view instanceof LinearLayout)
            {
                ViewGroup parentRow = (ViewGroup) view;
                for (int viewIndex2 = 0; viewIndex2 < parentRow.getChildCount(); viewIndex2++)
                {
                    View view2 = parentRow.getChildAt(viewIndex2);
                    if (view2 instanceof  CheckBox)
                    {
                        parentRequirementCheckBox = (CheckBox) view2;
                    }
                    else if (view2 instanceof TextView)
                    {
                        parentRequirementTextView = (TextView) view2;
                    }
                }

                break;
            }
        }

        if (requirement.getParentRequirement().isCompleted()) {
            parentRequirementTextView.setTextColor(Color.parseColor(COMPLETED_GREEN));
            parentRequirementCheckBox.setChecked(true);
        }
        else if (requirement.getParentRequirement().isInProgress())
        {
            parentRequirementTextView.setTextColor(Color.parseColor(IN_PROGRESS_YELLOW));
            parentRequirementCheckBox.setChecked(false);
        }
        else
        {
            parentRequirementTextView.setTextColor(Color.BLACK);
            parentRequirementCheckBox.setChecked(false);
        }
    }

    private class CoachingStepNegativeListener implements DialogInterface.OnClickListener
    {
        private CheckBox checkbox;

        public CoachingStepNegativeListener(CheckBox checkbox) {
            this.checkbox = checkbox;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            checkbox.setChecked(false);
        }
    }


    private void displayCoachingStep(final Requirement requirement, final CheckBox currentCheckBox)
    {
        LinkedList<String> coachingSteps = requirement.getCoachingSteps();
        String coachingStep = coachingSteps.peek();

        final boolean isLastCoachingStep;
        if (coachingSteps.peekLast().equals(coachingStep))
        {
            isLastCoachingStep = true;
        }
        else
        {
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
        String[] osArray = { "Settings", "In Progress Requirements" };
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
                        Intent b = new Intent(ChecklistActivity.this, InProgressActivity.class);
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
