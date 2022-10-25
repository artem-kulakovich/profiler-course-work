package by.bntu.fitr.metric.database;

import by.bntu.fitr.core.CollectBasicMetricService;
import by.bntu.fitr.core.MetricRegistry;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.exception.CantConnectToDatabaseException;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class DataBaseCollectServiceBasic implements CollectBasicMetricService {
    private final MetricRegistry metricRegistry = MetricRegistry.getInstance();
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final ConnectionService connectionService;

    public DataBaseCollectServiceBasic(final ConnectionService connectionService) {
        this.connectionService = connectionService;
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
            connectionService.getConnection(propertyContext.getDataBaseHost(), propertyContext.getDataBaseUser(),
                    propertyContext.getDataBasePassword());
            long executionEndTime = System.currentTimeMillis();

            dataBaseMetric.setExecutionTimeStart(executionStartTime);
            dataBaseMetric.setExecutionTimeEnd(executionEndTime);
            dataBaseMetric.setStartedDate(now);
            metricRegistry.getDataBaseMetricRegistry().addMetric(dataBaseMetric);
        } catch (SQLException e) {
            throw new CantConnectToDatabaseException(ExceptionConstant.CANT_CONNECT_TO_DATABASE_EXCEPTION);
        }

    }

}
