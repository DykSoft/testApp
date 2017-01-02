package ru.javawebinar.webapp.sql;

import ru.javawebinar.webapp.WebAppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kdeni on 25.12.2016.
 */
public class Sql {

    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        
        execute(sql, new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
/*
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.execute();

        } catch (SQLException e) {

            throw new WebAppException("SQL failed: " + sql, e);
        }*/

    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

             return executor.execute(ps);

        } catch (SQLException e) {

            throw new WebAppException("SQL failed: " + sql, e);
        }


    }

    public <T> T execute(String sql, int resultSetType, int resultSetConcurrency, SqlExecutor<T> executor) {

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, resultSetType, resultSetConcurrency)) {

            return executor.execute(ps);

        } catch (SQLException e) {

            throw new WebAppException("SQL failed: " + sql, e);
        }


    }
}
