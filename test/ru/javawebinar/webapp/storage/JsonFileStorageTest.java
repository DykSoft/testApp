package ru.javawebinar.webapp.storage;

/**
 * denis
 * 27.11.2016.
 */
public class JsonFileStorageTest extends AbstractStorageTest {

    {

        storage = new JsonFileStorage("./file_storage");
    }

}