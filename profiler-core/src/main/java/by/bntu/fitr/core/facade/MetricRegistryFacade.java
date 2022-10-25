package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.MetricRegistry;
import by.bntu.fitr.core.TimedMetricRegistry;
import by.bntu.fitr.metric.database.DataBaseMetricRegistry;

public class MetricRegistryFacade implements PreRequestFacade {
    private final MetricRegistry metricRegistry = MetricRegistry.getInstance();

    @Override
    public void loadOnStartUp() {
        metricRegistry.registryTimedMetric(new TimedMetricRegistry());
        metricRegistry.registryDataBaseMetric(new DataBaseMetricRegistry());
    }
}
