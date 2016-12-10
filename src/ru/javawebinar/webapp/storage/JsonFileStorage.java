package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.util.JsonParser;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * denis
 * 27.11.2016.
 */
public class JsonFileStorage extends FileStorage {


    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {

        try(Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {

            JsonParser.write(resume, w);
        }

    }

    @Override
    protected Resume read(InputStream is) throws IOException {

        try (Reader r = new InputStreamReader(is,StandardCharsets.UTF_8)){

            return JsonParser.read(r,Resume.class);

        }
    }


    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
