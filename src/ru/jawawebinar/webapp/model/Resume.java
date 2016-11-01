package ru.jawawebinar.webapp.model;

import java.util.List;


/**
 * denis
 * 27.10.2016.
 */
public class Resume {

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;

    private List<Contact> contacts;
    private List<Section> sections;

    public Resume(String fullName, String location) {
        this.fullName = fullName;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    // private List<Organization> organizations;

    /*public Resume(String fullName, String location, List<Contact> contacts, List<Organization> organizations, List<Section> sections) {
        this.contacts = contacts;
        this.fullName = fullName;
        this.location = location;
        this.organizations = organizations;
        this.sections = sections;


    }*/
}
