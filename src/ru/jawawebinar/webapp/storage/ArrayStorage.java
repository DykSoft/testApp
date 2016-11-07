package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;


/**
 * denis
 * 01.11.2016.
 */
//public class ArrayStorage implements IStorage {
public class ArrayStorage extends AbstractStorage {

    private static final int LIMIT = 100;
    //protected Logger LOGGER = Logger.getLogger(getClass().getName());
    //private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    @Override
    public void clear() {

/*     for (int i = 0; i < LIMIT; i++) {

         array[i] = null;

     }*/

        //Эквивавелент

        logger.info("Delete all resumes.");
        Arrays.fill(array, null);
        size = 0;


    }

/*    @Override
    public void save(Resume r) {


        logger.info("Save resume with uuid=" + r.getUuid());
        int idx = getIndex(r.getUuid());


*//*
            try {
                throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
            } catch (WebAppException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE,e.getMessage(),e);
                throw new IllegalStateException(e);
            }
*//*
        if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + "already exist");
        array[size++] = r;


    }*/

    @Override
    protected void doSave(Resume r) {

        int idx = getIndex(r.getUuid());


/*
            try {
                throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
            } catch (WebAppException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE,e.getMessage(),e);
                throw new IllegalStateException(e);
            }
*/
        if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + "already exist");
        array[size++] = r;



    }

    @Override
    public void update(Resume r) {

        /*int idx = -1;

        for (int i = 0; i < LIMIT; i++) {

            Resume resume = array[i];
            if (resume != null && r.equals(resume)) {
                array[i] = r;
                idx = i;
            }

        }

        if (idx < 0) {
            new IllegalStateException("No resume found!");
        }*/

        logger.info("Update resume with uuid=" + r.getUuid());
        int idx = getIndex(r.getUuid());
        if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + "not exist");
        array[idx] = r;



    }

    @Override
    public Resume load(String uuid) {

        /*for (int i = 0; i < LIMIT; i++) {

            Resume resume = array[i];
            if (resume != null) {

                String resumeUuid = resume.getUuid();
                if (resumeUuid != null && resumeUuid.equals(uuid)) {
                    return resume;
                }


            }

        }*/

        logger.info("Load resume with uuid=" + uuid);
        int idx = getIndex(uuid);
        if (idx == -1) throw new WebAppException("Resume " + uuid + "not exist");
        return array[idx];
    }

    @Override
    public void delete(String uuid) {

       /* int idx = -1;

        for (int i = 0; i < LIMIT; i++) {
            Resume resume = array[i];
            if (resume != null) {
                String resumeUuid = resume.getUuid();
                if (resumeUuid != null && resumeUuid.equals(uuid)) {
                    array[i] = null;
                    idx = i;
                }
            }

        }

        if (idx < 0) {
            new IllegalStateException("Resume not found");
        }*/

        logger.info("Delete resume with uuid=" + uuid);
        int idx = getIndex(uuid);
        if (idx == -1) throw new WebAppException("Resume " + uuid + "not exist");
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx+1, array, idx,numMoved);
        array[--size] = null; // clear to let GC do its work



    }

    @Override
    public Collection<Resume> getAllSorted() {
        //return null;
        Arrays.sort(array,0,size);
        return Arrays.asList(Arrays.copyOf(array,size));
    }

    @Override
    public int size() {

       /* int size = 0;

        for (int i = 0; i < LIMIT; i++) {

            if (array[i] != null) {
                size++;
            }

        }
*/
        return size;
    }

    private int getIndex(String uuid) {

        for (int i = 0; i < LIMIT; i++) {

            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }


            }

        }


        return -1;
    }
}
