package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;

/**
 * denis
 * 01.11.2016.
 */
public interface IStorage {

    void clear();
    void save(Resume r);
    void update(Resume r);
    Resume load(String uuid);
    void delete(String uuid);
    Collection<Resume> getAllSorted();
    int size();
    boolean isSectionSupported();



}
