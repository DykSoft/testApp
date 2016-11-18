package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.Resume;

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

    protected void write(File file, Resume r) {

        //auto closable resources
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(r);
        } catch (IOException e) {
            throw new WebAppException("Could' t write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {

        Resume r = new Resume();

        try (InputStream is = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is)) {

           return (Resume)ois.readObject();

        } catch (IOException e) {

            throw new WebAppException("File not found " + file.getAbsolutePath(), e);

        } catch (ClassNotFoundException e) {

            throw new WebAppException("Error read resume ", e);
        }


    }


}
