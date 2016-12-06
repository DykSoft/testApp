package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.*;
import ru.jawawebinar.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * denis
 * 26.11.2016.
 */
public class XmlFileStorage extends FileStorage {

    private XmlParser xmlParser;


    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class, TextSection.class, Organization.Period.class);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {

        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {

            xmlParser.marshall(resume, w);
        }

    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {

            return xmlParser.unmarshall(r);

        }
    }


    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
