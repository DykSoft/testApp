package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.Resume;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

        map.replace(r.getUuid(),r);

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
    public Collection<Resume> doGetAllSorted()  {

        throw new WebAppException("Illegal state operation!");
    }

    @Override
    public int doSize() {

       return map.size();
    }
}
