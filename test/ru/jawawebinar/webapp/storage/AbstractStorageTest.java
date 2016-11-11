package ru.jawawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.jawawebinar.webapp.model.Contact;
import ru.jawawebinar.webapp.model.ContactType;
import ru.jawawebinar.webapp.model.Resume;

import java.util.Arrays;

/**
 * denis
 * 11.11.2016.
 */
public class AbstractStorageTest {

    private Resume R1, R2, R3;
    private ArrayStorage storage = new ArrayStorage();
    private MapStorage map = new MapStorage();



    @Before
    public void setUp() throws Exception {

        storage.clear();
        map.clear();

        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(new Contact(ContactType.MAIL, "mail1@ya.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "11111"));
        R2 = new Resume("Полное Имя2", null);
        R2.addContact(new Contact(ContactType.SKYPE, "skype2"));
        R2.addContact(new Contact(ContactType.PHONE, "22222"));
        R3 = new Resume("Полное Имя3", null);

        storage.save(R1);
        storage.save(R2);
        storage.save(R3);

        map.save(R1);
        map.save(R2);
        map.save(R3);


    }

    @Test
    public void save() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
        Assert.assertEquals(3, storage.size());

        map.clear();
        Assert.assertEquals(0, map.size());
        map.save(R3);
        map.save(R1);
        map.save(R2);
        Assert.assertEquals(3, map.size());


    }

    @Test
    public void delete() throws Exception {

        storage.delete(R1.getUuid());
        Assert.assertEquals(2,storage.size());

        map.delete(R1.getUuid());
        Assert.assertEquals(2,map.size());

    }

    @Test
    public void update() throws Exception {

        R2.setFullName("Update R2");

        storage.update(R2);
        Assert.assertEquals(R2,storage.load(R2.getUuid()));

        map.update(R2);
        Assert.assertEquals(R2,map.load(R2.getUuid()));

    }

    @Test
    public void clear() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());

        map.clear();
        Assert.assertEquals(0, map.size());

    }

    @Test
    public void load() throws Exception {

        Assert.assertEquals(R1,storage.load(R1.getUuid()));
        Assert.assertEquals(R2,storage.load(R2.getUuid()));
        Assert.assertEquals(R3,storage.load(R3.getUuid()));

        Assert.assertEquals(R1,map.load(R1.getUuid()));
        Assert.assertEquals(R2,map.load(R2.getUuid()));
        Assert.assertEquals(R3,map.load(R3.getUuid()));

    }

    @Test
    public void getAllSorted() throws Exception {

        Resume[] src = new Resume[]{R1,R2,R3};
        Arrays.sort(src);
        Assert.assertArrayEquals(src,storage.getAllSorted().toArray());

    }

    @Test
    public void size() throws Exception {

        Assert.assertEquals(true, storage.size() == 3);
        Assert.assertEquals(true, map.size() == 3);

    }

}