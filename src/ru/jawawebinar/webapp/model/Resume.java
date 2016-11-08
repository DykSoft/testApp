package ru.jawawebinar.webapp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


/**
 * denis
 * 27.10.2016.
 */
public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;
    private String location;
    private String homePage;

    private List<Contact> contacts = new LinkedList<>();
    private List<Section> sections = new LinkedList<>();

    public Resume(String fullName, String location) {
        //this.fullName = fullName;
        //this.location = location;
        this(UUID.randomUUID().toString(),fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.fullName = fullName;
        this.location = location;
        this.uuid = uuid;
    }

    public void addSection(Section section) {
        sections.add(section);

    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getLocation() {
        return location;
    }

    public List<Section> getSections() {
        return sections;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(uuid);
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        //return Objects.equals(this.uuid, other.uuid);
        return uuid.equals(other.uuid);
    }

    /*    @Override
    public int hashCode() {
        //return Objects.hash(uuid, fullName, location, homePage, contacts, sections);
        return uuid.hashCode();
    }*/



/*    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return Objects.equals(this.uuid, other.uuid)
                && Objects.equals(this.fullName, other.fullName)
                && Objects.equals(this.location, other.location)
                && Objects.equals(this.homePage, other.homePage)
                && Objects.equals(this.contacts, other.contacts)
                && Objects.equals(this.sections, other.sections);
    }*/

/*    public void setUuid(String uuid) {
        this.uuid = uuid;
    }*/

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }


    /*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }*/



    // private List<Organization> organizations;

    /*public Resume(String fullName, String location, List<Contact> contacts, List<Organization> organizations, List<Section> sections) {
        this.contacts = contacts;
        this.fullName = fullName;
        this.location = location;
        this.organizations = organizations;
        this.sections = sections;


    }*/
}
