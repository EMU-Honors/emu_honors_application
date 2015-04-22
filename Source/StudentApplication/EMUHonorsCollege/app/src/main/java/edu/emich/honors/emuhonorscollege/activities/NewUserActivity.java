package edu.emich.honors.emuhonorscollege.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.emich.honors.emuhonorscollege.R;
import edu.emich.honors.emuhonorscollege.connection.DB_Handler;
import edu.emich.honors.emuhonorscollege.datatypes.FieldOfStudy;
import edu.emich.honors.emuhonorscollege.datatypes.GraduationDate;
import edu.emich.honors.emuhonorscollege.datatypes.HonorsHandbook;
import edu.emich.honors.emuhonorscollege.datatypes.User;
import edu.emich.honors.emuhonorscollege.datatypes.enums.AcademicProgram;
import edu.emich.honors.emuhonorscollege.datatypes.enums.GraduationTerm;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HandbookYear;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class NewUserActivity extends ActionBarActivity {

    private Button submitButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText eIDEditText;
    private CheckBox departmentalHonorsCheckBox;
    private CheckBox universityHonorsCheckBox;
    private CheckBox highestHonorsCheckBox;
    private CheckBox fullHonorsCheckBox;
    private Spinner handbookYearSpinner;
    private Spinner graduationTermSpinner;
    private Spinner graduationYearSpinner;
    private Spinner majorSpinner;
    private Spinner minorSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);


        handbookYearSpinner = (Spinner) findViewById(R.id.handbookYearSpinner);
        graduationTermSpinner = (Spinner) findViewById(R.id.expectedGraduationTermSpinner);
        graduationYearSpinner = (Spinner) findViewById(R.id.expectedGraduationYearSpinner);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        minorSpinner = (Spinner) findViewById(R.id.minorSpinner);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
        eIDEditText = (EditText) findViewById(R.id.eIDEditText);
        departmentalHonorsCheckBox = (CheckBox) findViewById(R.id.departmentalHonorsCheckBox);
        universityHonorsCheckBox = (CheckBox) findViewById(R.id.universityHonorsCheckBox);
        highestHonorsCheckBox = (CheckBox) findViewById(R.id.highestHonorsCheckBox);
        fullHonorsCheckBox = (CheckBox) findViewById(R.id.fullHonorsCheckBox);
        submitButton = (Button) findViewById(R.id.new_user_submit);

        handbookYearSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, HandbookYear.values()));
        graduationTermSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, GraduationTerm.values()));
        graduationYearSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, GraduationDate.getListOfCurrentGraduationYears()));
        majorSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, AcademicProgram.values()));
        minorSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_template, AcademicProgram.values()));
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

    public void submitNewUser(View view) {
        String email = emailEditText.getText().toString().toLowerCase();
        char[] password = passwordEditText.getText().toString().toCharArray();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String eID = eIDEditText.getText().toString();

        FieldOfStudy fieldOfStudy = buildFieldOfStudy();
        GraduationDate graduationDate = buildGraduationDate();

        ArrayList<HonorsType> honorsTypes = null;

        try {
            honorsTypes = buildHonorsTypes();
        } catch (Exception e) {
            Log.println(Log.DEBUG, "New User Guardrail", e.getMessage());
        }


        JSONObject userdata = new JSONObject();
        try {
            userdata.put("username", emailEditText.getText());
            userdata.put("password", passwordEditText.getText());
            userdata.put("year", handbookYearSpinner.getSelectedItem().toString());
            userdata.put("honors_type", honorsTypes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), handbookYearSpinner.getPrompt().toString(), Toast.LENGTH_LONG).show();
        Log.d("ugh", handbookYearSpinner.getSelectedItem().toString());

        DB_Handler instance = DB_Handler.getInstance(getApplicationContext());
        instance.newUser(userdata);

//        User newUser = new User(email, password, firstName, lastName, HonorsHandbook.getSampleHandbook(), honorsTypes, eID, fieldOfStudy, graduationDate);
        User newUser = new User(email, password, firstName, lastName,
                new HonorsHandbook((HandbookYear) handbookYearSpinner.getSelectedItem(), honorsTypes.get(0)),
                honorsTypes, eID, fieldOfStudy, graduationDate);

        //Show attributes of the newly created User
        showNewUserInformationDialog(newUser);
    }

    private void showNewUserInformationDialog(User user)
    {
        String honorsTypesString = "";

        for (HonorsType type : user.getHonorsTypes()) {
            honorsTypesString += type.toString() + "\n";
        }

        String newUserSummaryText =
                "First Name: " + user.getFirstName() +
                        "\nLast Name: " + user.getLastName() +
                        "\nEmail: " + user.getEmail() +
                        "\nPassword: " + user.getPassword() +
                        "\nEID: " + user.getEID() +
                        "\nHandbook Year: " + user.getHandbook().getHandbookYear() +
                        "\nHonors Types: " + honorsTypesString +
                        "\nMajor: " + user.getFieldOfStudy().getMajors().get(0).toString() +
                        "\nMinor: " + user.getFieldOfStudy().getMinors().get(0).toString() +
                        "\nGraduation Date: " + user.getGraduationDate().getTerm() + " " + user.getGraduationDate().getYear();

        AlertDialog.Builder newUserSummaryDialog = new AlertDialog.Builder(this);
        newUserSummaryDialog.setTitle("New User Summary");
        newUserSummaryDialog.setMessage(newUserSummaryText);
        newUserSummaryDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_LONG).show();
            }
        });

        newUserSummaryDialog.show();
    }

    private GraduationDate buildGraduationDate() {
        GraduationTerm term = (GraduationTerm) graduationTermSpinner.getSelectedItem();
        String year = (String) graduationYearSpinner.getSelectedItem();

        return new GraduationDate(term, year);
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
}
