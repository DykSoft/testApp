package ru.jawawebinar.webapp.model;

import java.util.Collection;

/**
 * denis
 * 24.10.2016.
 */
public class Achievement extends AbstractResume {

    private Collection<String> collection;

    public Achievement(String str) {
        achievement = str;
    }

    public Collection<String> getCollection() {
        return collection;
    }

    @Override
    public void setDescription(String str) {
         this.collection.add(str);
    }

    @Override
    public void getDescription() {
        super.getDescription();

        System.out.println("\n" + achievement + ": ");

        for(String str: collection) {

          System.out.println("\n" + str);

        }

    }
}
