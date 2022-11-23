package by.bntu.fitr.core.metric.registry;


import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.exception.handler.ProfilerCoreExceptionHandler;

import java.util.*;


public class MetricRegistry {
    private static MetricRegistry instance;
    private final ProfilerCoreExceptionHandler profilerCoreExceptionHandler = ProfilerCoreExceptionHandler.getInstance();
    private Map<String, AbstractMetricRegistry> metricRegistryMap;

    public static synchronized MetricRegistry getInstance() {
        if (instance == null) {
            instance = new MetricRegistry();
        }
        return instance;
    }

    public synchronized void registryMetric(final AbstractMetricRegistry metricRegistry, final String registryName) {
        if (metricRegistryMap == null) {
            metricRegistryMap = new HashMap<>();
        }
        metricRegistryMap.put(registryName, metricRegistry);
    }

    public Map<String, AbstractMetricRegistry> getMetricRegistryMap() {
        return metricRegistryMap;
    }

    public AbstractMetricRegistry getMetricRegistry(final String name) {
        return Optional.of(metricRegistryMap.get(name)).orElseThrow(() -> {
            profilerCoreExceptionHandler.handle(ExceptionConstant.CANT_GET_METRIC_REGISTRY_EXCEPTION);
            return null;
        });
    }
}
