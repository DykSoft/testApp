package ru.javawebinar.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kdeni on 25.12.2016.
 */

public interface ConnectionFactory {

    Connection getConnection() throws SQLException;

}
