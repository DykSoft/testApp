package ru.jawawebinar.webapp.model;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * denis
 * 25.10.2016.
 */
public class Education extends AbstractResume{

    private Collection<String> collection;
    private Date date1;
    private Date date2;

    public Education(String str) {
        education = str;
    }

    public void setDate(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;

    }

    public String getDateStrFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1, Locale.getDefault());
        return "from " +  dateFormat.format(this.date1) + " on " + dateFormat.format(this.date2);
    }

    @Override
    public void setDescription(String str) {

        this.collection.add(getDateStrFormat());
        this.collection.add(str);

    }

    @Override
    public void getDescription() {
        super.getDescription();


        System.out.println("\n" + education + ": ");

        for(String str: collection) {

            System.out.println("\n" + str);

        }


    }
}
