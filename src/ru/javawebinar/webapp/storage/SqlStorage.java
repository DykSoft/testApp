package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.sql.ConnectionFactory;
import ru.javawebinar.webapp.sql.Sql;
import ru.javawebinar.webapp.sql.SqlExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by kdeni on 22.12.2016.
 */
public class SqlStorage implements IStorage {

    public Sql sql;


    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            }
        });
    }

    @Override
    public void clear() {

        sql.execute("DELETE FROM resume");

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume load(String uuid) {
       return sql.execute("SELECT * FROM resume r WHERE r.uuid = ?", new SqlExecutor<Resume>() {
           @Override
           public Resume execute(PreparedStatement st) throws SQLException {
               return null;
           }
       });
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
