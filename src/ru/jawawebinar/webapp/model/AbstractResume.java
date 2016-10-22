package ru.jawawebinar.webapp.model;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * denis
 * 15.10.2016.
 */
public abstract class AbstractResume implements Resume {

    public static final String DATE_FORMAT1 = "dd.MM.yyyy";

    protected String firstName;
    protected String lastName;
    protected String middleName;
    //protected String fullName;
    protected Date birthDay;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return firstName + " " + lastName + " " + middleName;
    }

/*    public void setFullName(String fullName) {
        this.fullName = fullName;
    }*/

    public Date getBirthDay() {
        return birthDay;
    }

    public String getStrBirthDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1, Locale.getDefault());
        return dateFormat.format(getStrBirthDay());
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void getDescription() {
        out("\n" + getFullName() + ", birthday: " + getStrBirthDay());
    }

    private void out(String str) {

        System.out.println(str);

    }

    /*
    String fullName;
    String[] some;
    Collection<String> collection;
    */


}
