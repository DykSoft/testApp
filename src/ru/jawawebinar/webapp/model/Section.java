package ru.jawawebinar.webapp.model;

/**
 * denis
 * 29.10.2016.
 */
enum TypeSection {

    OBJECTIVE,
    ACHIEVEMENT,
    QUALIFICATION,
    EXPERIENCE,
    EDUCATION
}


public class Section {

    private TypeSection typeSection;
    private String value;

    public Section(TypeSection typeSection, String value) {
        this.typeSection = typeSection;
        this.value = value;
    }

    public TypeSection getTypeSection() {
        return typeSection;
    }

    public void setTypeSection(TypeSection typeSection) {
        this.typeSection = typeSection;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
