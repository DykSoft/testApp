package ru.javawebinar.webapp.storage;

/**
 * denis
 * 26.11.2016.
 */
public class XmlFileStorageTest extends AbstractStorageTest {

    {

        storage = new XmlFileStorage("./file_storage");
    }

}