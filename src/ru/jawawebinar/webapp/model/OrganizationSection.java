package ru.jawawebinar.webapp.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * denis
 * 01.11.2016.
 */

public class OrganizationSection extends Section {

    private List<Organization> values;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... values) {
        this.values = new LinkedList<>(Arrays.asList(values));
    }

    public List<Organization> getValues() {
        return values;
    }

    public void setValues(List<Organization> values) {
        this.values = values;
    }
}
