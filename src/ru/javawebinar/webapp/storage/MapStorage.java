package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * denis
 * 07.11.2016.
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(String uuid, Resume r) {
        map.put(uuid, r);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected void doUpdate(String uuid, Resume r) {
        map.put(uuid, r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }


    @Override
    public void doClear() {

        map.clear();

    }


    @Override
    public List<Resume> doGetAllSorted() {

        return new ArrayList<>(map.values());

    }

    @Override
    public int doSize() {

        return map.size();
    }

    @Override
    protected boolean exist(String uuid) {
        return map.containsKey(uuid);
        //return map.get(uuid) != null;
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
