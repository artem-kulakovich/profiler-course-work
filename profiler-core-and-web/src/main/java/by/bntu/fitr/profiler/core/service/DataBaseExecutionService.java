package by.bntu.fitr.profiler.core.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseExecutionService {

    Connection getConnection(final String host, final String user, final String password) throws SQLException;

    void performSelectSql(final String sql, final Object... params);
}
