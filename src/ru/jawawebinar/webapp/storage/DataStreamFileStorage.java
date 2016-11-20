package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {

                dos.writeInt(entry.getKey().ordinal());

                if (entry.getValue().getClass().equals(TextSection.class)) {

                    dos.writeInt(-1);

                    TextSection section = (TextSection) entry.getValue();
                    dos.writeUTF(section.getTitle());
                    dos.writeUTF(section.getComment());


                } else if (entry.getValue().getClass().equals(MultiTextSection.class)) {

                    MultiTextSection section = (MultiTextSection) entry.getValue();
                    dos.writeInt(section.getValues().size());

                    for (String str : section.getValues()) {
                        dos.writeUTF(str);
                    }

                }


            }


        } catch (
                IOException e
                )

        {
            throw new WebAppException("Could' t write file " + file.getAbsolutePath(), r, e);
        }

    }

    protected Resume read(File file) {

        Resume r = new Resume();
        r.setUuid(file.getName());

        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {

            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());

            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                r.addContact(ContactType.VALUES[dis.readInt()], dis.readUTF());
            }

            int sectiosSize = dis.readInt();
            for (int i = 0; i < sectiosSize; i++) {

                SectionType st = SectionType.VALUES[dis.readInt()];

                int multiTextSectionSize = dis.readInt();

                if (multiTextSectionSize > 0) {

                    List<String> list = new ArrayList<>();

                    for (int j = 0; j < multiTextSectionSize; j++) {

                        list.add(dis.readUTF());
                    }

                    r.addSection(st, new MultiTextSection(list));

                } else if (multiTextSectionSize == -1) {
                    r.addSection(st, new TextSection(dis.readUTF(), dis.readUTF()));
                }
            }

        } catch (IOException e) {

            throw new WebAppException("File not found " + file.getAbsolutePath(), e);
        }

        return r;
    }


}
