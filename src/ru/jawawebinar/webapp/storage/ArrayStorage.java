package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;


/**
 * denis
 * 01.11.2016.
 */

public class ArrayStorage extends AbstractStorage {

    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    @Override
    public void doClear() {

        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume r) {

        int idx = getIndex(r.getUuid());
        //if (idx != -1) throw new WebAppException("Resume " + r.getUuid() + "already exist");
        array[size++] = r;

    }

    @Override
    public void doUpdate(Resume r) {

        int idx = getIndex(r.getUuid());
        //if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + "not exist");
        array[idx] = r;

    }

    @Override
    public Resume doLoad(String uuid) {

        int idx = getIndex(uuid);
        //if (idx == -1) throw new WebAppException("Resume " + uuid + "not exist");
        return array[idx];
    }


    @Override
    protected void doDelete(String uuid) {

        int idx = getIndex(uuid);
        //if (idx == -1) throw new WebAppException("Resume " + uuid + "not exist");
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx+1, array, idx,numMoved);
        array[--size] = null; // clear to let GC do its work

    }

    @Override
    public List<Resume> doGetAllSorted() {

        //Arrays.sort(array,0,size);
        return Arrays.asList(Arrays.copyOf(array,size));
    }


    @Override
    public int doSize() {

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

    @Override
    protected boolean exist(String uuid) {
        return (getIndex(uuid) != -1);
    }
}
