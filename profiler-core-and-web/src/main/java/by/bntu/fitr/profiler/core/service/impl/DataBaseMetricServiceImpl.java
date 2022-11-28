package by.bntu.fitr.profiler.core.service.impl;



import by.bntu.fitr.profiler.core.constant.CommonConstant;
import by.bntu.fitr.profiler.core.constant.ExceptionConstant;
import by.bntu.fitr.profiler.core.context.PropertyContext;
import by.bntu.fitr.profiler.core.exception.handler.ProfilerCoreExceptionHandler;
import by.bntu.fitr.profiler.core.metric.DataBaseMetric;
import by.bntu.fitr.profiler.core.metric.registry.DataBaseMetricRegistry;
import by.bntu.fitr.profiler.core.metric.registry.MetricRegistry;
import by.bntu.fitr.profiler.core.service.DataBaseMetricService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataBaseMetricServiceImpl implements DataBaseMetricService {
    private final DataBaseMetricRegistry dbMetricRegistry = (DataBaseMetricRegistry) MetricRegistry.getInstance().getMetricRegistry(CommonConstant.DATABASE_METRIC_REGISTRY);
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final ProfilerCoreExceptionHandler profilerCoreExceptionHandler = ProfilerCoreExceptionHandler.getInstance();
    private DataBaseExecutionServiceImpl dataBaseExecutionServiceImpl;

    public DataBaseMetricServiceImpl(final DataBaseExecutionServiceImpl dataBaseExecutionServiceImpl) {
        this.dataBaseExecutionServiceImpl = dataBaseExecutionServiceImpl;
    }

    @Override
    public void getConnectionTimeExecution() {
        if (!propertyContext.isDataBaseMetricShouldBeCollected()) {
            return;
        }

        try {
            DataBaseMetric dataBaseMetric = new DataBaseMetric();
            LocalDateTime now = LocalDateTime.now();

            long executionStartTime = System.currentTimeMillis();
            dataBaseExecutionServiceImpl.getConnection(propertyContext.getDataBaseHost(), propertyContext.getDataBaseUser(),
                    propertyContext.getDataBasePassword());
            long executionEndTime = System.currentTimeMillis();

            dataBaseMetric.setExecutionTimeStart(executionStartTime);
            dataBaseMetric.setExecutionTimeEnd(executionEndTime);
            dataBaseMetric.setConnectionName(propertyContext.getDataBaseHost());
            dataBaseMetric.setMethodName("getConnectionTimeExecution");
            dataBaseMetric.setMetricType(CommonConstant.DATABASE_METRIC_TYPE);
            dbMetricRegistry.addMetric(dataBaseMetric);
        } catch (SQLException e) {
            profilerCoreExceptionHandler.handle(ExceptionConstant.CANT_CONNECT_TO_DATABASE_EXCEPTION);
        }

    }

    @Override
    public void getSqlTimeExecution(final String sql, final Object... params) {
        long start = System.currentTimeMillis();
        dataBaseExecutionServiceImpl.performSelectSql(sql, params);
        long end = System.currentTimeMillis();
        DataBaseMetric dataBaseMetric = new DataBaseMetric();
        dataBaseMetric.setMetricType(CommonConstant.DATABASE_METRIC_TYPE);
        dataBaseMetric.setMethodName("getSqlTimeExecution");
        dataBaseMetric.setExecutionTimeEnd(end);
        dataBaseMetric.setExecutionTimeStart(start);
        dbMetricRegistry.addMetric(dataBaseMetric);
    }

}
