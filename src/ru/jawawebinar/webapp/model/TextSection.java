package ru.jawawebinar.webapp.model;

import java.io.Serializable;

/**
 * denis
 * 01.11.2016.
 */
public class TextSection extends Section implements Serializable {
    private String title;
    private String comment;

    public TextSection(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }
}
