package ru.jawawebinar.webapp.model;

import java.util.List;

/**
 * denis
 * 01.11.2016.
 */
public class MultiTextSection extends Section {

    private List<String> values;

    public MultiTextSection(List<String> values) {
        this.values = values;
    }
}
