package ru.javawebinar.webapp.model;

import java.io.Serializable;

/**
 * denis
 * 31.10.2016.
 */
public enum ContactType implements Serializable {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    ICQ("ICQ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ContactType[]VALUES = ContactType.values();
}