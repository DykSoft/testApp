package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;


/**
 * denis
 * 01.11.2016.
 */

public class ArrayStorage extends AbstractStorage<Integer> {

    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    @Override
    public void doClear() {

        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected Resume doLoad(Integer idx, String uuid) {
        return array[idx];
    }


    @Override
    protected void doSave(Integer ctx, Resume r) {
        array[size++] = r;
    }

    @Override
    protected void doDelete(Integer idx, String uuid) {
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx+1, array, idx,numMoved);
        array[--size] = null; // clear to let GC do its work
    }

    @Override
    protected void doUpdate(Integer idx, Resume r) {
        array[idx] = r;
    }

    @Override
    public List<Resume> doGetAllSorted() {
        return Arrays.asList(Arrays.copyOf(array,size));
    }


    @Override
    public int doSize() {

        return size;
    }

    @Override
    protected Integer getContext(String uuid) {

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
    protected boolean exist(Integer idx) {
        return idx != -1;
    }

}
