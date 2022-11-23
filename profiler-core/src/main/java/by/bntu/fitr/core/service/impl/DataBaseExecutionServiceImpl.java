package by.bntu.fitr.core.service.impl;


import by.bntu.fitr.core.service.DataBaseExecutionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseExecutionServiceImpl implements DataBaseExecutionService {

    @Override
    public Connection getConnection(final String host, final String user, final String password) throws SQLException {
        return DriverManager.getConnection(host, user, password);
    }
}
