package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.model.Resume;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * denis
 * 07.11.2016.
 */
abstract class AbstractStorage implements IStorage{

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void save(Resume r) {

        logger.info("Save resume with uuid=" + r.getUuid());
        //TODO try to move here exception treatment
        doSave(r);

    }

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);
        //TODO try to move here exception treatment
        doDelete(uuid);

    }

    @Override
    public void update(Resume r) {

        logger.info("Update resume with uuid=" + r.getUuid());
        //TODO try to move here exception treatment
        doUpdate(r);

    }

    @Override
    public void clear() {

        logger.info("Delete all resumes.");
        //TODO try to move here exception treatment
        doClear();

    }

    @Override
    public Resume load(String uuid) {

        logger.info("Load resume with uuid=" + uuid);
        //TODO try to move here exception treatment
        return doLoad(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {

        logger.info("All sorted resume");
        //TODO try to move here exception treatment
        return doGetAllSorted();
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
    protected abstract Collection<Resume> doGetAllSorted();
    protected abstract int doSize();

}
