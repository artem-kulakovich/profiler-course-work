package by.bntu.fitr.profiler.core.constant;

public interface CommonConstant {
    //Profiler properties constants.
    String PROPERTY_FILE_NAME = "profiler.properties";
    String PROFILER_SCAN_PACKAGES_ATTR = "profiler.metrics.scan-packages";
    String PROFILER_DATABASE_CONNECTION_HOST = "profiler.metrics.database.connection.host";
    String PROFILER_DATABASE_CONNECTION_USER = "profiler.metrics.database.connection.user";
    String PROFILER_DATABASE_CONNECTION_PASSWORD = "profiler.metrics.database.connection.password";
    String PROFILER_METRICS_COLLECT_DATABASE = "profiler.metrics.collect-database";

    //Util constants.
    String SLASH_SEPARATOR = "/";
    String DOT_SEPARATOR = "\\.";

    //Core constants.
    String METRIC_ANNOTATION_NAME = "Metric";
    String TIMED_ANNOTATION_NAME = "Timed";
    String DATABASE_METRIC_REGISTRY = "DATABASE_REGISTRY";
    String TIMED_METRIC_REGISTRY = "TIMED_REGISTRY";
    String GAUGE_REGISTRY = "GAUGE_REGISTRY";
    String TIMED_METRIC_TYPE = "Timed metric";
    String DATABASE_METRIC_TYPE = "Database metric";

}
