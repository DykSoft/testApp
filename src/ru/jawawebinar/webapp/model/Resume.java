package ru.jawawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;


/**
 * denis
 * 27.10.2016.
 */
//public class Resume implements Comparable<Resume> {

// TODO add Serializable and serialVersionUID to all model classes
 @XmlRootElement
 @XmlAccessorType(XmlAccessType.FIELD)
 public class Resume implements Serializable {

    static final long serialVersionUID = 1L;

    //private final String uuid;
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;

    //private List<Contact> contacts = new LinkedList<>();
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public static final Resume EMPTY;

    static {
        EMPTY = new Resume();
/*        for(SectionType type: SectionType.values()) {
            EMPTY.addSection(type,type.getSectionClass().getEmptySection());
        }*/


    }


    public Resume() {
    }

    public Resume(String fullName, String location) {
        //this.fullName = fullName;
        //this.location = location;
        this(UUID.randomUUID().toString(),fullName, location);
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String fullName, String location) {

        Objects.requireNonNull(uuid,"uuid is null");
        Objects.requireNonNull(fullName,"full name is null");
        Objects.requireNonNull(location,"location is null");

        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;

    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);

    }

/*    public void addContact(Contact contact) {
        contacts.add(contact);
    }*/

    public void addContact(ContactType type, String value) {
        contacts.put(type,value);
    }

/*    public List<Contact> getContacts() {
        return contacts;
    }*/

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
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

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    //@Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "fullName='" + fullName + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public void addObjective(String value) {
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }

    public void addMultiTextSection(SectionType type, String... values) {
        addSection(type, new MultiTextSection(values));
    }

    public void addOrganizationSection(SectionType sectionType,  Organization... organizations) {

        addSection(sectionType, new OrganizationSection(organizations));

    }

    /*    private String getEmail(List<Contact> list) {
        for(Contact c: list) {
            if(c.getType() == ContactType.MAIL) {
                return c.getValue();
            }

        }

        return null;
    }*/


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
