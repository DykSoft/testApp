package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;
import java.io.*;

/**
 * denis
 * 17.11.2016.
 */
public class SerializeFileStorage extends FileStorage {

    private File dir;

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
             oos.writeObject(r);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {

        try (ObjectInputStream ois = new ObjectInputStream(is)) {

           return (Resume)ois.readObject();

        } catch (ClassNotFoundException e) {

            throw new WebAppException("Error read resume ", e);
        }


    }


}
