package by.bntu.fitr.core;

import java.util.Stack;

public class TimedMetricRegistry {
    private Stack<TimedMetric> timedMetrics;

    public Stack<TimedMetric> getTimedMetricList() {
        return timedMetrics;
    }

    public synchronized void addMetric(TimedMetric timedMetric) {
        if (timedMetrics == null) {
            timedMetrics = new Stack<>();
        }
        timedMetrics.add(timedMetric);
    }

    public synchronized TimedMetric getTimedMetric() {
        return timedMetrics.get(timedMetrics.size() - 1);
    }

    public Stack<TimedMetric> getTimedMetrics() {
        return timedMetrics;
    }
}
