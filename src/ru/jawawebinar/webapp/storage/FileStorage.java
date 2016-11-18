package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.ContactType;
import ru.jawawebinar.webapp.model.Resume;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * denis
 * 17.11.2016.
 */
public class FileStorage extends AbstractStorage<File> {

    private File dir;

    public FileStorage(String path) {

        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalStateException("'" + path + "' is not directory or is not writable");
        }

    }

    @Override
    protected void doSave(File file, Resume r) {

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), r, e);
        }

        write(file, r);

    }

    protected void write(File file, Resume r) {

        //TODO fix NullPointerException

        //auto closable resources
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeUTF(r.getFullName());
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());

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

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted");
        }

    }

    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);

    }

    @Override
    protected void doClear() {

        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            doDelete(file);
        }


    }

    @Override
    protected Resume doLoad(File file) {

        return read(file);
    }

    @Override
    protected List<Resume> doGetAllSorted() {

        // TODO read all

        return null;
    }

    @Override
    protected int doSize() {

        String[] list = dir.list();

        if(list == null) throw new WebAppException("Could't read directory " + dir.getAbsolutePath());

        return list.length;
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir,fileName);
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }
}
