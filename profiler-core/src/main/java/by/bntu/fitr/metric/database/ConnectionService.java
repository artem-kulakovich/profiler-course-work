package by.bntu.fitr.metric.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionService {

    public Connection getConnection(final String host, final String user, final String password) throws SQLException {
        return DriverManager.getConnection(host, user, password);
    }
}
