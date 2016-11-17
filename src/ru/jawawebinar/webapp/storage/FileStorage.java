package ru.jawawebinar.webapp.storage;

import ru.jawawebinar.webapp.WebAppException;
import ru.jawawebinar.webapp.model.Resume;

import java.io.File;
import java.util.List;

/**
 * denis
 * 17.11.2016.
 */
public class FileStorage extends AbstractStorage<File> {

    private File dir;

    public FileStorage(String path) {

        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalStateException("'" + path + "' is not directory or is not writable");
        }

    }

    @Override
    protected void doSave(File file, Resume r) {

    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted");
        }

    }

    @Override
    protected void doUpdate(File file, Resume r) {

    }

    @Override
    protected void doClear() {

        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file: files) {
            doDelete(file);
        }


    }

    @Override
    protected Resume doLoad(File file) {
        return null;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return null;
    }

    @Override
    protected int doSize() {
        return 0;
    }

    @Override
    protected File getContext(String fileName) {
        return new File(fileName) ;
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }
}
