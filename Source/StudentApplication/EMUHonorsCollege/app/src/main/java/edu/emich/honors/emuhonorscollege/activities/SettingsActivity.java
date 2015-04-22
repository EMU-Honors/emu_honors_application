package edu.emich.honors.emuhonorscollege.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import edu.emich.honors.emuhonorscollege.HonorsApplication;
import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.datatypes.FieldOfStudy;
import edu.emich.honors.emuhonorscollege.datatypes.GraduationDate;
import edu.emich.honors.emuhonorscollege.datatypes.User;
import edu.emich.honors.emuhonorscollege.datatypes.enums.AcademicProgram;
import edu.emich.honors.emuhonorscollege.datatypes.enums.GraduationTerm;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class SettingsActivity extends ActionBarActivity {

    //dummy data variables
    ArrayList<HonorsType> hTypeTest = new ArrayList<>();
    ArrayList<AcademicProgram> aMajorTest = new ArrayList<>();
    ArrayList<AcademicProgram> aMinorTest = new ArrayList<>();
    FieldOfStudy fOfStudyTest = new FieldOfStudy(aMajorTest, aMinorTest);
    GraduationDate gradTest = new GraduationDate(GraduationTerm.FALL, "2015");
    char pasTest[];
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private CheckBox departmentalHonorsCheckBox;
    private CheckBox universityHonorsCheckBox;
    private CheckBox highestHonorsCheckBox;
    private CheckBox fullHonorsCheckBox;
    private Spinner handbookYearSpinner;
    private Spinner majorSpinner;
    private Spinner minorSpinner;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private User currentUser = User.getSampleUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        handbookYearSpinner = (Spinner) findViewById(R.id.handbookYearSpinner);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        minorSpinner = (Spinner) findViewById(R.id.minorSpinner);
        oldPasswordEditText = (EditText) findViewById(R.id.password);
        newPasswordEditText = (EditText) findViewById(R.id.confirm_password);

        departmentalHonorsCheckBox = (CheckBox) findViewById(R.id.departmentalHonorsCheckBox);
        universityHonorsCheckBox = (CheckBox) findViewById(R.id.universityHonorsCheckBox);
        highestHonorsCheckBox = (CheckBox) findViewById(R.id.highestHonorsCheckBox);
        fullHonorsCheckBox = (CheckBox) findViewById(R.id.fullHonorsCheckBox);
        Button submitButton = (Button) findViewById(R.id.new_settings_submit);

        //dummy data

        hTypeTest.add(HonorsType.DEPARTMENTAL);
        hTypeTest.add(HonorsType.HIGHEST);
        aMajorTest.add(AcademicProgram.COMPUTER_SCIENCE);
        aMinorTest.add(AcademicProgram.MATH);
        fOfStudyTest.setMajors(aMajorTest);
        fOfStudyTest.setMinors(aMinorTest);
        String test = "password";
        pasTest = test.toCharArray();

        currentUser.setFieldOfStudy(fOfStudyTest);
        currentUser.setPassword(pasTest);
        currentUser.setHonorsTypes(hTypeTest);
        currentUser.setGraduationDate(gradTest);


        //setting checkboxes
        ArrayList<HonorsType> honorsCheck = currentUser.getHonorsTypes();
        for (int i = 0; i < honorsCheck.size(); i++) {
            if (honorsCheck.get(i) == HonorsType.DEPARTMENTAL)
                departmentalHonorsCheckBox.setChecked(true);

            if (honorsCheck.get(i) == HonorsType.UNIVERSITY)
                universityHonorsCheckBox.setChecked(true);

            if (honorsCheck.get(i) == HonorsType.HIGHEST)
                highestHonorsCheckBox.setChecked(true);

            if (honorsCheck.get(i) == HonorsType.FULL)
                fullHonorsCheckBox.setChecked(true);
        }

        handbookYearSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, HandbookYear.values()));
        majorSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, AcademicProgram.values()));
        minorSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, AcademicProgram.values()));

        /* navigation drawer settings */
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void submitSettings(View view) {
//        boolean same = false;
//        String oldPassword = oldPasswordEditText.getText().toString();
//        char[] newPassword = newPasswordEditText.getText().toString().toCharArray();
//        String confirmPass = currentUser.getPassword().toString();
//
//
//        if (oldPassword.equals(confirmPass))
//            currentUser.setPassword(newPassword);
//
//
//        FieldOfStudy fieldOfStudy = buildFieldOfStudy();
//
//        currentUser.setFieldOfStudy(fieldOfStudy);
//
//        ArrayList<HonorsType> honorsTypes = null;
//
//        try {
//            honorsTypes = buildHonorsTypes();
//        } catch (Exception e) {
//            Log.println(Log.DEBUG, "New User Guardrail", e.getMessage());
//        }
//
//        currentUser.setHonorsTypes(honorsTypes);
//
//        //Show attributes of the newly created User
//        String honorsTypesString = "";
//
//        for (HonorsType type : currentUser.getHonorsTypes()) {
//            honorsTypesString += type.toString() + "\n";
//        }
//
//        String newUserSummaryText =
//                "First Name: " + currentUser.getFirstName() +
//                        "\nLast Name: " + currentUser.getLastName() +
//                        "\nEmail: " + currentUser.getEmail() +
//                        "\nPassword: " + currentUser.getPassword().toString() +
//                        "\nEID: " + currentUser.getEID() +
//                        "\nHonors Types: " + honorsTypesString +
//                        "\nMajor: " + currentUser.getFieldOfStudy().getMajors().get(0).toString() +
//                        "\nMinor: " + currentUser.getFieldOfStudy().getMinors().get(0).toString() +
//                        "\nGraduation Date: " + currentUser.getGraduationDate().getTerm() + " " + currentUser.getGraduationDate().getYear();
//
//        AlertDialog.Builder newUserSummaryDialog = new AlertDialog.Builder(this);
//        newUserSummaryDialog.setTitle("New User Summary");
//        newUserSummaryDialog.setMessage(newUserSummaryText);
//        newUserSummaryDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        newUserSummaryDialog.show();
    }

    private FieldOfStudy buildFieldOfStudy() {
        AcademicProgram major = (AcademicProgram) majorSpinner.getSelectedItem();
        AcademicProgram minor = (AcademicProgram) minorSpinner.getSelectedItem();

        return new FieldOfStudy(major, minor);
    }

    private ArrayList<HonorsType> buildHonorsTypes() throws Exception {
        ArrayList<HonorsType> newUserHonorsTypes = new ArrayList<>();

        if (departmentalHonorsCheckBox.isChecked()) {
            newUserHonorsTypes.add(HonorsType.DEPARTMENTAL);
        }
        if (universityHonorsCheckBox.isChecked()) {
            newUserHonorsTypes.add(HonorsType.UNIVERSITY);
        }
        if (highestHonorsCheckBox.isChecked()) {
            newUserHonorsTypes.add(HonorsType.HIGHEST);
        }
        if (fullHonorsCheckBox.isChecked()) {
            newUserHonorsTypes.add(HonorsType.FULL);
        }

        if (newUserHonorsTypes.isEmpty()) {
            throw new Exception("No Honors Type selected.");
        }

        return newUserHonorsTypes;
    }

    public void displayFullHonorsDialog(View view) {
        fullHonorsCheckBox.setChecked(false);

        AlertDialog.Builder fullHonorsDialog = new AlertDialog.Builder(this);
        fullHonorsDialog.setTitle("Full Honors Not Supported");
        fullHonorsDialog.setMessage("Full Honors is not supported by the application at this time. Please contact your Honors Advisor for assistance.");
        fullHonorsDialog.show();
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

    private void addDrawerItems() {
        String[] osArray = {"Checklist", "Back to Login"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent a = new Intent(SettingsActivity.this, ChecklistActivity.class);
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(SettingsActivity.this, LoginActivity.class);
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
}
