package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.ContactType;
import ru.jawawebinar.webapp.model.Resume;

import java.io.*;
import java.util.Map;

/**
 * denis
 * 17.11.2016.
 */
public class DataStreamFileStorage extends FileStorage {

    private File dir;

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {

        //auto closable resources
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeUTF(String.valueOf(r.getFullName()));
            dos.writeUTF(String.valueOf(r.getLocation()));
            dos.writeUTF(String.valueOf(r.getHomePage()));

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                //dos.writeUTF(entry.getKey().name() + ": " + entry.getValue());
                dos.writeUTF(entry.getValue());

            }

            //TODO add sectuion

        } catch (IOException e) {
            throw new WebAppException("Could' t write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {

        Resume r = new Resume();

        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {

            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());

            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                r.addContact(ContactType.VALUES[dis.readInt()], dis.readUTF());
            }

            // TODO add sections

        } catch (IOException e) {

            throw new WebAppException("File not found " + file.getAbsolutePath(), e);
        }

        return null;

    }


}
