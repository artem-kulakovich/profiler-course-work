package by.bntu.fitr.profiler.core.service;


import by.bntu.fitr.profiler.core.bytecode.ByteCodeExecutor;

public interface ProfilerCoreService {

    void initializeContext(final ClassLoader classLoader);

    void registerMetric(final String metricType);

    void redefineClasses(final ClassLoader classLoader, final String packageForScan, final String annotationName);

    void setPropertyService(final PropertyService propertyService);

    void setClassCollectorService(final ClassCollectorService classCollectorService);

    void setByteCodeExecutor(final ByteCodeExecutor byteCodeExecutor);
}
