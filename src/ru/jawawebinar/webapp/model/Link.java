package ru.jawawebinar.webapp.model;

import java.io.Serializable;

/**
 * denis
 * 27.10.2016.
 */
public class Link implements Serializable {

    static final long serialVersionUID = 1L;

    public static Link EMPTY = new Link();
    private final String name;
    private final String url;


    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link(Link link) {
        this(link.name,link.url);
/*
        this.name = link.name;
        this.url = link.url;*/
    }

    public Link() {
        this("",null);
    }

    public static Link empty() {
        return EMPTY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!name.equals(link.name)) return false;
        return url != null ? url.equals(link.url) : link.url == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
