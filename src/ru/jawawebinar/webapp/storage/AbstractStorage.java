package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * denis
 * 07.11.2016.
 */
abstract class AbstractStorage implements IStorage {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void save(Resume r) {

        logger.info("Save resume with uuid=" + r.getUuid());
        if (exist(r.getUuid())) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist");
        } else {
            doSave(r);
        }

    }

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);
        if (!exist(uuid)) {
            throw new WebAppException("Resume " + uuid + "not exist");
        } else {
            doDelete(uuid);
        }

    }

    @Override
    public void update(Resume r) {

        logger.info("Update resume with uuid=" + r.getUuid());
        if (!exist(r.getUuid())) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist");
        } else {
            doUpdate(r);
        }

    }

    @Override
    public void clear() {

        logger.info("Delete all resumes.");
        doClear();

    }

    @Override
    public Resume load(String uuid) {

        logger.info("Load resume with uuid=" + uuid);

        if (!exist(uuid)) {
            throw new WebAppException("Resume " + uuid + "not exist");
        }

        return doLoad(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {

        logger.info("All sorted resume");
        List<Resume> list = doGetAllSorted();
        //Collections.sort(list);
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if (cmp != 0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
            }

        });

        //лямда
        /*
            Collections.sort(list, (Resume o1, Resume o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return o1.getUuid().compareTo(o2.getUuid());
        });

        */

        return list;
    }


    @Override
    public int size() {

        logger.info("Get size");

        return doSize();
    }

    protected abstract void doSave(Resume r);

    protected abstract void doDelete(String uuid);

    protected abstract void doUpdate(Resume r);

    protected abstract void doClear();

    protected abstract Resume doLoad(String uuid);

    protected abstract List<Resume> doGetAllSorted();

    protected abstract int doSize();

    protected abstract boolean exist(String uuid);

}
