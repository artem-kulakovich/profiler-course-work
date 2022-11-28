package by.bntu.fitr.profiler.core.metric;

import java.time.LocalDateTime;

public abstract class AbstractMetric {
    private LocalDateTime startedDate = LocalDateTime.now();
    private String methodName;
    private String metricType;

    protected LocalDateTime getStartedDate() {
        return startedDate;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(final String metricType) {
        this.metricType = metricType;
    }
}
