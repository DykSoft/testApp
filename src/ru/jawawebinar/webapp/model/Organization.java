package ru.jawawebinar.webapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * denis
 * 29.10.2016.
 */
public class Organization {

    public static final String DATE_FORMAT1 = "dd.MM.yyyy";

    private Date dateS;
    private Date dateE;
    private String value;

    public Organization(Date dateS, Date dateE, String value) {
        this.dateE = dateE;
        this.dateS = dateS;
        this.value = value;
    }

    public Date getDateE() {
        return dateE;
    }

    public void setDateE(Date dateE) {
        this.dateE = dateE;
    }

    public Date getDateS() {
        return dateS;
    }

    public void setDateS(Date dateS) {
        this.dateS = dateS;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFullValue() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1, Locale.getDefault());

        return getValue() + " c " + dateFormat.format(getDateS()) + " по "
                          + (dateE == null ? " наст. время" : dateFormat.format(getDateE()));

    }
}
