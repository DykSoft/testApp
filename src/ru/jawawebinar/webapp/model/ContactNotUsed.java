package ru.jawawebinar.webapp.model;

/**
 * denis
 * 29.10.2016.
 */
public class ContactNotUsed {

    private final ContactType type;
    private final String value;

    public ContactNotUsed(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
