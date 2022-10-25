package by.bntu.fitr.core;


import by.bntu.fitr.metric.database.DataBaseMetricRegistry;


public class MetricRegistry {
    private static MetricRegistry instance;
    private TimedMetricRegistry timedMetricRegistry;
    private DataBaseMetricRegistry dataBaseMetricRegistry;
    private GaugeRegistry gaugeRegistry;

    public static synchronized MetricRegistry getInstance() {
        if (instance == null) {
            instance = new MetricRegistry();
        }
        return instance;
    }

    public synchronized void registryTimedMetric(final TimedMetricRegistry timedMetricRegistry) {
        this.timedMetricRegistry = timedMetricRegistry;
    }

    public synchronized void registryDataBaseMetric(final DataBaseMetricRegistry dataBaseMetricRegistry) {
        this.dataBaseMetricRegistry = dataBaseMetricRegistry;
    }

    public synchronized void registryGauge(final GaugeRegistry gaugeRegistry) {
        this.gaugeRegistry = gaugeRegistry;
    }

    public TimedMetricRegistry getTimedMetricRegistry() {
        return timedMetricRegistry;
    }

    public DataBaseMetricRegistry getDataBaseMetricRegistry() {
        return dataBaseMetricRegistry;
    }

    public GaugeRegistry getGaugeRegistry() {
        return gaugeRegistry;
    }
}
