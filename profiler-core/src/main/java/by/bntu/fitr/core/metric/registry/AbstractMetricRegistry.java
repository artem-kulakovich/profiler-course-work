package by.bntu.fitr.core.metric.registry;

import by.bntu.fitr.core.metric.Gauge;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
