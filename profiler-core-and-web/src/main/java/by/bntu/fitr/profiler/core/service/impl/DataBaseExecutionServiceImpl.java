package by.bntu.fitr.profiler.core.service.impl;



import by.bntu.fitr.profiler.core.context.PropertyContext;
import by.bntu.fitr.profiler.core.service.DataBaseExecutionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DataBaseExecutionServiceImpl implements DataBaseExecutionService {
    private final PropertyContext propertyContext = PropertyContext.getInstance();

    @Override
    public Connection getConnection(final String host, final String user, final String password) throws SQLException {
        return DriverManager.getConnection(host, user, password);
    }

    @Override
    public void performSelectSql(final String sql, final Object... params) {
        if (isItSelectQuery(sql)) {
            try {
                Connection connection = getConnection(propertyContext.getDataBaseHost(),
                        propertyContext.getDataBaseUser(), propertyContext.getDataBasePassword());
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                populateParams(preparedStatement, sql);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isItSelectQuery(final String sql) {
        return sql.toUpperCase().equals("SELECT");
    }

    private void populateParams(final PreparedStatement preparedStatement, final Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i, params[i]);
        }
    }
}
