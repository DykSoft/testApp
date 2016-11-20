package ru.jawawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.*;

import java.util.*;

/**
 * denis
 * 11.11.2016.
 */
abstract public class AbstractStorageTest {

    private Resume R1, R2, R3;
    protected IStorage storage;


    @Before
    public void setUp() throws Exception {

        storage.clear();

        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");

        List<String> list = new ArrayList<>();
        list.add("adadadad123");
        list.add("dadadadad434");
        list.add("fsdfsf4545");

        Section multiSection = new MultiTextSection(list);
        R1.addSection(SectionType.ACHIEVEMENT,multiSection);

        Section textSection = new TextSection("Я был здесь!","jsdnahdfk nfdnsf");
        R1.addSection(SectionType.OBJECTIVE,textSection);

        R2 = new Resume("Полное Имя2", null);
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3", null);

        storage.save(R1);
        storage.save(R2);
        storage.save(R3);

        //TODO add section

    }

    @Test
    public void save() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
        Assert.assertEquals(3, storage.size());

    }

    @Test(expected = WebAppException.class)
    public void savePresented() throws Exception {
        storage.save(R1);
    }

    @Test
    public void delete() throws Exception {

        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test(expected = WebAppException.class)
    public void deleteMissed() throws Exception {
        storage.delete("dummy");
    }


    @Test
    public void update() throws Exception {

        R2.setFullName("Update R2");

        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));

    }

    @Test(expected = WebAppException.class)
    public void updateMissed() throws Exception {

        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);

    }


    @Test
    public void clear() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void load() throws Exception {

        Assert.assertEquals(R1, storage.load(R1.getUuid()));
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
        Assert.assertEquals(R3, storage.load(R3.getUuid()));

    }

    @Test
    public void getAllSorted() throws Exception {

/*        Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());*/

        List<Resume> list = Arrays.asList(R1,R2,R3);
        //Collections.sort(list);
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return 0;
            }
        });
        Assert.assertEquals(list,storage.getAllSorted());

    }

    @Test
    public void size() throws Exception {

        Assert.assertEquals(true, storage.size() == 3);

    }

}