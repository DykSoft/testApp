package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.sql.ConnectionFactory;
import ru.javawebinar.webapp.sql.Sql;
import ru.javawebinar.webapp.sql.SqlExecutor;

import java.sql.*;
import java.util.Collection;

import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * Created by kdeni on 22.12.2016.
 */
public class SqlStorage implements IStorage {

    public Sql sql;


    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public void clear() {

        sql.execute("DELETE FROM resume");

    }

    @Override
    public void save(Resume r) {

        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.setString(3, r.getLocation());
            ps.setString(4, r.getHomePage());
            ps.execute();
            return null;
        });


    }

    @Override
    public void update(Resume r) {

        sql.execute("UPDATE resume SET full_name = ?, location = ?, home_page = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getLocation());
            ps.setString(3, r.getHomePage());
            ps.setString(4, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new WebAppException("Resume not found", r);
            };
            return null;
        });

    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT * FROM resume r WHERE r.uuid = ?", new SqlExecutor<Resume>() {
            @Override
            public Resume execute(PreparedStatement ps) throws SQLException {

                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new WebAppException("Resume " + uuid + " is not exist");
                }

                Resume r = new Resume(uuid,
                        rs.getString("full_name"),
                        rs.getString("location"),
                        rs.getString("home_page"));

                return r;
            }
        });
    }

    @Override
    public void delete(String uuid) {

        sql.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new WebAppException("Resume " + uuid + " not exist", uuid);
            }
            return null;
        });

    }

    @Override
    public Collection<Resume> getAllSorted() {

        //sql.execute("SELECT * FROM resume r ORDER BY r.full_name, r.uuid");

        return null;
    }

    @Override
    public int size() {

        return sql.execute("SELECT count(*) FROM resume", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });

    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
