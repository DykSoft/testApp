package ru.jawawebinar.webapp.model;

import java.io.Serializable;

/**
 * denis
 * 31.10.2016.
 */
public enum SectionType implements Serializable {
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATION("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static SectionType[] VALUES = SectionType.values();
}
