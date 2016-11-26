package ru.jawawebinar.webapp.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * denis
 * 01.11.2016.
 */
public class TextSection extends Section implements Serializable {

    static final long serialVersionUID = 1L;

    private String value;

    public TextSection() {
    }

    public TextSection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSection other = (TextSection) obj;
        return Objects.equals(this.value, other.value);
    }

    /*private String title;
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

    @Override
    public int hashCode() {
        return Objects.hash(title, comment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSection other = (TextSection) obj;
        return Objects.equals(this.title, other.title)
                && Objects.equals(this.comment, other.comment);
    }*/
}
