package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * denis
 * 17.11.2016.
 */
public class DataStreamFileStorage extends FileStorage {

    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {

        try (final DataOutputStream dos = new DataOutputStream(os)) {

            writeString(dos, r.getUuid());
            writeString(dos, r.getFullName());
            writeString(dos, r.getLocation());
            writeString(dos, r.getHomePage());

            Map<ContactType, String> contacts = r.getContacts();
   /*         dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                //dos.writeUTF(entry.getValue());
                writeString(dos,entry.getValue());
            }*/

   /*         writeCollection(dos, contacts.entrySet(), new ElementWriter<Map.Entry<ContactType, String>>() {
                @Override
                public void write(Map.Entry<ContactType, String> entry) throws IOException {

                    dos.writeInt(entry.getKey().ordinal());
                    writeString(dos, entry.getValue());

                }

            });
*/
            writeCollection(dos, contacts.entrySet(), entry -> {

                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());

            });

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {

                SectionType sectionType = entry.getKey();
                dos.writeInt(sectionType.ordinal());
                Section section = entry.getValue();

                if (section.getClass().equals(TextSection.class)) {

                    dos.writeInt(-1);
                    writeString(dos, ((TextSection) section).getValue());

                } else if (section.getClass().equals(MultiTextSection.class)) {

        /*            dos.writeInt(((MultiTextSection)section).getValues().size());

                    for (String str : ((MultiTextSection)section).getValues()) {
                        //dos.writeUTF(str);
                        writeString(dos,str);
                    }*/

        /*            writeCollection(dos, ((MultiTextSection) section).getValues(), new ElementWriter<String>() {
                        @Override
                        public void write(String value) throws IOException {
                               writeString(dos,value);
                        }
                    });
*/
                    writeCollection(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos, value));

                }


            }


        }

    }


    /*protected Resume read(File file) {*/
    @Override
    protected Resume read(InputStream is) throws IOException {

        Resume r = new Resume();

        try (DataInputStream dis = new DataInputStream(is)) {

            r.setUuid(readString(dis));
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));

            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                r.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }

            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {

                SectionType sectionType = SectionType.VALUES[dis.readInt()];

                int multiTextSectionSize = dis.readInt();

                if (multiTextSectionSize > 0) {

      /*              List<String> list = new ArrayList<>(multiTextSectionSize);

                    for (int j = 0; j < multiTextSectionSize; j++) {

                        //list.add(dis.readUTF());
                        list.add(readString(dis));
                    }

                    r.addSection(sectionType, new MultiTextSection(list));*/

/*                    List<String> list = readList(dis, new ElementReader<String>() {
                        @Override
                        public String read() throws IOException {
                            return readString(dis);
                        }
                    });

                    r.addSection(sectionType, new MultiTextSection(list));*/

                    r.addSection(sectionType, new MultiTextSection(readList(dis, multiTextSectionSize, () -> readString(dis))));

                } else if (multiTextSectionSize == -1) {
                    r.addSection(sectionType, new TextSection(readString(dis)));
                }
            }

        }

        return r;
    }


    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;

    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;

    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {

        dos.writeInt(collection.size());

        for (T item : collection) {
            writer.write(item);
        }

    }

    private <T> List<T> readList(DataInputStream dis, int size, ElementReader<T> reader) throws IOException {

        //int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }

        return list;

    }

}
