package edu.emich.honors.emuhonorscollege.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import edu.emich.honors.emuhonorscollege.datatypes.enums.GraduationTerm;
import edu.emich.honors.emuhonorscollege.datatypes.enums.HonorsType;

public class User implements Serializable{

    private String email;
    private char[] password;
    private String firstName;
    private String lastName;
    private HonorsHandbook handbook;
    private ArrayList<HonorsType> honorsTypes;
    private String eID;
    private FieldOfStudy fieldOfStudy;
    private GraduationDate graduationDate;


    public User(String email, char[] password, String firstName, String lastName, HonorsHandbook handbook, ArrayList<HonorsType> honorsTypes, String eID, FieldOfStudy fieldOfStudy, GraduationDate graduationDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.handbook = handbook;
        this.honorsTypes = honorsTypes;
        this.eID = eID;
        this.fieldOfStudy = fieldOfStudy;
        this.graduationDate = graduationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEID() {
        return eID;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public char[] getPassword() {return password;}

    public ArrayList<HonorsType> getHonorsTypes() {
        return honorsTypes;
    }

    public void setHonorsTypes(ArrayList<HonorsType> honorsTypes) {
        this.honorsTypes = honorsTypes;
    }

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GraduationDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(GraduationDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public HonorsHandbook getHandbook() {
        return handbook;
    }

    public void setHandbook(HonorsHandbook handbook) {
        this.handbook = handbook;
    }

    public static User getSampleUser()
    {
        ArrayList<HonorsType> sampleHonorsType = new ArrayList<>(Arrays.asList(HonorsType.DEPARTMENTAL));
        User sampleUser = new User("jsmith@emich.edu", "password".toCharArray(), "John", "Smith", HonorsHandbook.getSampleHandbook(), sampleHonorsType, "E03487695", null, new GraduationDate(GraduationTerm.FALL, "2018"));
        return sampleUser;
    }
}
