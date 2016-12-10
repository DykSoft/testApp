package ru.javawebinar.webapp.storage;

/**
 * denis
 * 11.11.2016.
 */
public class SerializeFileStorageTest extends AbstractStorageTest {

    {
        storage = new SerializeFileStorage("./file_storage");
    }


}