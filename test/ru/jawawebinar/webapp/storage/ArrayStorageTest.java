package ru.jawawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.jawawebinar.webapp.model.*;

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

        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @org.junit.Test
    public void save() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        Assert.assertEquals(3, storage.size());

    }

    @org.junit.Test
    public void update() throws Exception {

        String uid = R3.getUuid();

        storage.update(R3);
        Resume resume = storage.load(uid);

        Assert.assertEquals(resume,R3);

    }

    @org.junit.Test
    public void load() throws Exception {

        Resume resume = storage.load(R2.getUuid());
        Assert.assertEquals(resume.getUuid(),R2.getUuid());

    }

    @org.junit.Test
    public void delete() throws Exception {


        String uid = R1.getUuid();
        storage.delete(uid);
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(null, storage.load(uid));



    }

    @org.junit.Test
    public void getAllSorted() throws Exception {

    }

    @org.junit.Test
    public void size() throws Exception {

        Assert.assertEquals(true, storage.size() == 3);

    }

}