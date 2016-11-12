package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.Resume;

import java.util.*;

/**
 * denis
 * 07.11.2016.
 */
public class MapStorage extends AbstractStorage {

    private Map<String,Resume> map = new HashMap<>();


    @Override
    protected void doSave(Resume r) {

        map.put(r.getUuid(),r);

    }

    @Override
    public void doClear() {

        map.clear();

    }

    @Override
    public void doUpdate(Resume r) {

        //map.replace(r.getUuid(),r);
        map.put(r.getUuid(),r);

    }

    @Override
    public Resume doLoad(String uuid) {

        return map.get(uuid);
    }

    @Override
    public void doDelete(String uuid) {

        map.remove(uuid);

    }

    @Override
    public List<Resume> doGetAllSorted()  {


/*        ArrayList<Resume> list = new ArrayList<>();
        for(Map.Entry<String,Resume> entry: map.entrySet()) {
            list.add(entry.getValue());
        }*/

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
}
