package by.bntu.fitr.profiler.core.metric.registry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMetricRegistry<T> {
    private List<T> metrics;

    public synchronized void addMetric(T metric) {
        if (metrics == null) {
            metrics = new ArrayList<>();
        }
        metrics.add(metric);
    }

    public List<T> getMetrics() {
        return metrics;
    }
}
