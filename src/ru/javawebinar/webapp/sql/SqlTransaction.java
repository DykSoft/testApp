package ru.javawebinar.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kdeni on 05.01.2017.
 */
public interface SqlTransaction<T> {

    T execute(Connection conn) throws SQLException;

}
