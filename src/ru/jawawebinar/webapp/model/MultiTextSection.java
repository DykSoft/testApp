package ru.jawawebinar.webapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * denis
 * 01.11.2016.
 */
public class MultiTextSection extends Section implements Serializable {

    private List<String> values;

    public MultiTextSection(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
