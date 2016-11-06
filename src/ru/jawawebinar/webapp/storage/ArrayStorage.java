package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.Resume;

import java.util.Collection;

/**
 * denis
 * 01.11.2016.
 */
public class ArrayStorage implements IStorage {

    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];
    int idx = 0;


    @Override
    public void clear() {

     for (int i = 0; i < LIMIT; i++) {

         array[i] = null;

     }


    }

    @Override
    public void save(Resume r) {

      int idx = -1;

      for (int i = 0; i < LIMIT; i++) {
          Resume resume = array[i];
          if (resume !=null) {
              if (r.equals(resume)) {
                  new IllegalStateException("Already present");
              }
          } else if (idx == -1) {
              idx = i;
          }
      }

      array[idx] = r;

/*
      for (int i = 0; i < LIMIT; i++) {

          if (array[i] == null) {
              array[i] = r;
          }

      }
*/

    }

    @Override
    public void update(Resume r) {

        int idx = -1;

        for (int i = 0; i < LIMIT; i++) {

            Resume resume = array[i];
            if (resume != null  && r.equals(resume)) {
                array[i] = r;
                idx = i;
            }

        }

        if (idx < 0) {
            new IllegalStateException("No resume found!");
        }

    }

    @Override
    public Resume load(String uuid) {

        for (int i = 0; i < LIMIT; i++) {

            Resume resume = array[i];
            if (resume != null) {

                String resumeUuid = resume.getUuid();
                if (resumeUuid != null && resumeUuid.equals(uuid)) {
                    return resume;
                }


            }

        }

        return null;
    }

    @Override
    public void delete(String uuid) {

        int idx = -1;

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
        }

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {

        int size = 0;

        for (int i = 0; i < LIMIT; i++) {

            if (array[i] != null) {
                size++;
            }

        }

        return size;
    }
}
