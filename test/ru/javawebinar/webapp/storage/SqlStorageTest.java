package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppConfig;

/**
 * denis
 * 26.11.2016.
 */
public class SqlStorageTest extends AbstractStorageTest {

    {

        storage = WebAppConfig.get().getStorage();
    }

}