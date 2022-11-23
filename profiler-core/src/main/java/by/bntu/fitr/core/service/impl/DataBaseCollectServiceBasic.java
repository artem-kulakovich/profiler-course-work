package by.bntu.fitr.core.service.impl;

import by.bntu.fitr.core.service.CollectBasicMetricService;
import by.bntu.fitr.core.metric.DataBaseMetric;
import by.bntu.fitr.core.metric.registry.MetricRegistry;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.context.PropertyContext;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataBaseCollectServiceBasic implements CollectBasicMetricService {
    private final MetricRegistry metricRegistry = MetricRegistry.getInstance();
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final DataBaseExecutionServiceImpl dataBaseExecutionServiceImpl;

    public DataBaseCollectServiceBasic(final DataBaseExecutionServiceImpl dataBaseExecutionServiceImpl) {
        this.dataBaseExecutionServiceImpl = dataBaseExecutionServiceImpl;
    }

    @Override
    public void collectMetric() {
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
            dataBaseMetric.setStartedDate(now);

           // metricRegistry.getDataBaseMetricRegistry().addMetric(dataBaseMetric);
        } catch (SQLException e) {
            throw new RuntimeException(ExceptionConstant.CANT_CONNECT_TO_DATABASE_EXCEPTION);
        }

    }

}
