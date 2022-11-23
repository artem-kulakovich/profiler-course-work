package by.bntu.fitr.core.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DataBaseExecutionService {

    Connection getConnection(final String host, final String user, final String password) throws SQLException;
}
