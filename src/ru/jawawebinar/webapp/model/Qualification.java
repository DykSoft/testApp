package ru.jawawebinar.webapp.model;


import java.util.Collection;

/**
 * denis
 * 24.10.2016.
 */
public class Qualification extends AbstractResume{

    private Collection<String> collection;

    public Qualification(String str) {
        qualification = str;
    }

    @Override
    public void setDescription(String str) {
       this.collection.add(str);

    }

    @Override
    public void getDescription() {
        super.getDescription();

        System.out.println("\n" + qualification + ": ");

        for(String str: collection) {

            System.out.println("\n" + str);

        }
    }
}
