package ru.jawawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.jawawebinar.webapp.model.Contact;
import ru.jawawebinar.webapp.model.ContactType;
import ru.jawawebinar.webapp.model.Resume;

/**
 * denis
 * 02.11.2016.
 */
public class ArrayStorageTest {

    private static Resume R1, R2, R3;

    private ArrayStorage storage = new ArrayStorage();

    static {

        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(new Contact(ContactType.MAIL, "mail1@ya.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "11111"));
        R2 = new Resume("Полное Имя2", null);
        R2.addContact(new Contact(ContactType.SKYPE, "skype2"));
        R2.addContact(new Contact(ContactType.PHONE, "22222"));
        R3 = new Resume("Полное Имя3", null);

    }

    @BeforeClass
    public static void beforeClass() {
        //the same as static {}

    }

    @Before
    public void before() {

        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);

    }


/*    {

       arrayStorage = new ArrayStorage();

    }

    static {

        ArrayStorage arrayStorage = new ArrayStorage();


    }*/


    @org.junit.Test
    public void clear() throws Exception {

    }

    @org.junit.Test
    public void save() throws Exception {


    }

    @org.junit.Test
    public void update() throws Exception {

    }

    @org.junit.Test
    public void load() throws Exception {

    }

    @org.junit.Test
    public void delete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(null, storage.load(R1.getUuid()));


    }

    @org.junit.Test
    public void getAllSorted() throws Exception {

    }

    @org.junit.Test
    public void size() throws Exception {

        Assert.assertEquals(3, storage.size() == 3);

    }

}