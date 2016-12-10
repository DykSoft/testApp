package ru.javawebinar.webapp.storage;

/**
 * denis
 * 11.11.2016.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest {

    {
        storage = new DataStreamFileStorage("./file_storage");
    }


}