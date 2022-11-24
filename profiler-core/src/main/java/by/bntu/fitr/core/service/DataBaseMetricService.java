package by.bntu.fitr.core.service;

public interface DataBaseMetricService {

    void getConnectionTimeExecution();

    void getSqlTimeExecution(final String sql, final Object... params);
}

