package by.bntu.fitr.metric.database;

import by.bntu.fitr.core.TimedMetric;

import java.util.Stack;

public class DataBaseMetricRegistry {
    private Stack<DataBaseMetric> dataBaseMetrics;

    public synchronized void addMetric(DataBaseMetric dataBaseMetric) {
        if (dataBaseMetric == null) {
            dataBaseMetrics = new Stack<>();
        }
        dataBaseMetrics.add(dataBaseMetric);
    }

    public synchronized DataBaseMetric getTimedMetric() {
        return dataBaseMetrics.get(dataBaseMetrics.size() - 1);
    }

    public Stack<DataBaseMetric> getDataBaseMetrics() {
        return dataBaseMetrics;
    }

}
