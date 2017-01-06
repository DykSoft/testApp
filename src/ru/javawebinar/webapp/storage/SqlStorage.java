package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.sql.ConnectionFactory;
import ru.javawebinar.webapp.sql.Sql;
import ru.javawebinar.webapp.sql.SqlExecutor;
import ru.javawebinar.webapp.sql.SqlTransaction;

import java.sql.*;
import java.util.*;

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

        sql.execute((SqlTransaction<Void>) conn -> {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.setString(3, r.getLocation());
                ps.setString(4, r.getHomePage());
                ps.execute();
            }

            insertContact(conn, r);
            return null;
        });

/*        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.setString(3, r.getLocation());
            ps.setString(4, r.getHomePage());
            ps.execute();
            return null;
        });*/


    }

    private void insertContact(Connection conn, Resume r) throws SQLException {

        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {

            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {

                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();

            }

            ps.executeBatch();

        }


    }

    private void deleteContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {

            ps.setString(1, r.getUuid());
            ps.execute();

        }
    }

    @Override
    public void update(Resume r) {

        sql.execute((SqlTransaction<Void>) conn -> {

            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ?, location = ?, home_page = ? WHERE uuid = ?")) {

                ps.setString(1, r.getFullName());
                ps.setString(2, r.getLocation());
                ps.setString(3, r.getHomePage());
                ps.setString(4, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new WebAppException("Resume not found", r);
                }

            }
            deleteContacts(conn,r);
            insertContact(conn, r);
            return null;
        });


       /* sql.execute("UPDATE resume SET full_name = ?, location = ?, home_page = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getLocation());
            ps.setString(3, r.getHomePage());
            ps.setString(4, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new WebAppException("Resume not found", r);
            }
            ;
            return null;
        });*/

    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute(
                "SELECT *\n" +
                "     FROM resume r\n" +
                "     LEFT JOIN contact c ON r.uuid = c.resume_uuid\n" +
                "    WHERE r.uuid = ?", ps -> {

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
        //TODO Проверить !!!!

        return sql.execute("SELECT * FROM resume r ORDER BY r.full_name, r.uuid",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ps -> {

                    ResultSet rs = ps.executeQuery();

                    if (!rs.first()) {
                        throw new WebAppException("Resumes is not found!");
                    }

                    List<Resume> list = new ArrayList<Resume>();

                    do {

                        list.add(new Resume(rs.getString("uuid"),
                                rs.getString("full_name"),
                                rs.getString("location"),
                                rs.getString("home_page")));


                    } while (rs.next());


                    return list;

                });

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
