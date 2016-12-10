package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GKislin
 * 09.01.2015.
 */

public class ConcurrentMapStorage extends AbstractStorage<String> {

    private Map<String, Resume> MAP = new ConcurrentHashMap<>();

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected boolean exist(String uuid) {
        return MAP.containsKey(uuid);
    }

    @Override
    protected void doClear() {
        MAP.clear();
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        MAP.put(uuid, r);
    }

    @Override
    protected void doUpdate(String uuid, Resume r) {
        MAP.put(uuid, r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return MAP.get(uuid);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return new ArrayList<>(MAP.values());
    }

    @Override
    protected int doSize() {
        return MAP.size();
    }

    @Override
    protected void doDelete(String uuid) {
        MAP.remove(uuid);
    }


    @Override
    public boolean isSectionSupported() {
        return true;
    }
}