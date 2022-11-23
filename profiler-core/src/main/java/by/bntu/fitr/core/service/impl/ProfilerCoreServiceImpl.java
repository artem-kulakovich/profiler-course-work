package by.bntu.fitr.core.service.impl;

import by.bntu.fitr.core.metric.registry.*;
import by.bntu.fitr.core.bytecode.ByteCodeExecutor;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.exception.handler.ProfilerCoreExceptionHandler;
import by.bntu.fitr.core.service.ClassCollectorService;
import by.bntu.fitr.core.service.PropertyService;
import by.bntu.fitr.core.service.ProfilerCoreService;
import by.bntu.fitr.core.util.CommonUtil;
import by.bntu.fitr.core.util.FileUtil;


import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProfilerCoreServiceImpl implements ProfilerCoreService {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final MetricRegistry metricRegistry = MetricRegistry.getInstance();
    private final ProfilerCoreExceptionHandler profilerCoreExceptionHandler = ProfilerCoreExceptionHandler.getInstance();
    private PropertyService propertyService;
    private ClassCollectorService classCollectorService;
    private ByteCodeExecutor byteCodeExecutor;

    public ProfilerCoreServiceImpl(final PropertyService propertyService,
                                   final ClassCollectorService classCollectorService,
                                   final ByteCodeExecutor byteCodeExecutor) {
        this.propertyService = propertyService;
        this.classCollectorService = classCollectorService;
        this.byteCodeExecutor = byteCodeExecutor;
    }

    @Override
    public void initializeContext(final ClassLoader classLoader) {
        //Getting path to profiler.properties to get attributes from it.
        URL rootPath = classLoader.getResource(CommonConstant.PROPERTY_FILE_NAME);
        profilerCoreExceptionHandler.handle(rootPath == null, true, ExceptionConstant.PROPERTY_FILE_NOF_FOUND_EXCEPTION);

        //Loading property file
        propertyService.loadProperties(rootPath.getPath());

        //Checking if the attribute value correct. This attribute may contain only true or false values.
        List<String> whiteListValues = Arrays.asList("true", "false");
        String isDatabaseMetricShouldBeCollected = propertyService.getProperty(CommonConstant.PROFILER_METRICS_COLLECT_DATABASE);

        profilerCoreExceptionHandler.handle(
                CommonUtil.isValueFromPropertiesCorrected(isDatabaseMetricShouldBeCollected, whiteListValues),
                false,
                ExceptionConstant.INCORRECT_VALUE_IN_PROPERTY_FILE_EXCEPTION);

        /*
            Setting information from properties to java class (ApplicationContext, which stores info about application core
            and PropertyContext, which stores information about values from profiler.properties file.)
         */
        propertyContext.setProperties(propertyService.getProperties());
        propertyContext.setPackageForScan(propertyService.getProperty(CommonConstant.PROFILER_SCAN_PACKAGES_ATTR));
        propertyContext.setDataBaseMetricShouldBeCollected(Boolean.parseBoolean(isDatabaseMetricShouldBeCollected));
        propertyContext.setDataBaseHost(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_HOST));
        propertyContext.setDataBaseUser(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_USER));
        propertyContext.setDataBasePassword(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_PASSWORD));
        applicationContext.setClassLoader(classLoader);
    }

    @Override
    public void registerMetric(final String metricType) {
        registerMetricType(metricType);
    }

    @Override
    public void redefineClasses(final ClassLoader classLoader, final String packageForScan, final String annotationName) {
        //Getting path to first class from which to start the search for another classes.
        URL rootPath = classLoader.getResource(FileUtil.changeSeparator(propertyContext.getPackageForScan(),
                CommonConstant.DOT_SEPARATOR,
                CommonConstant.SLASH_SEPARATOR)
        );
        profilerCoreExceptionHandler.handle(rootPath == null, true, ExceptionConstant.NOT_VALID_PACKAGE_FOR_SCAN_ATTR);

        //Getting classesNames which were started from root path.
        List<String> classesName = classCollectorService.getClassesNameWithPackages(rootPath.getPath(), packageForScan);
        //Getting classes which are marked with Metric annotation.
        List<Class> classes = classCollectorService.getClassesMarkedWithAnnotation(CommonConstant.METRIC_ANNOTATION_NAME,
                classesName);
        //Getting method marked with specific annotation.
        Map<Class, List<String>> classMethods = classCollectorService.getMethodsMarkedWithAnnotations(
                annotationName,
                classes);
        //Change byte-code dynamicly.
        byteCodeExecutor.redefineMethodForTimedMetric(classMethods);
    }


    @Override
    public void setPropertyService(final PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public void setClassCollectorService(final ClassCollectorService classCollectorService) {
        this.classCollectorService = classCollectorService;
    }

    @Override
    public void setByteCodeExecutor(final ByteCodeExecutor byteCodeExecutor) {
        this.byteCodeExecutor = byteCodeExecutor;
    }

    private void registerMetricType(final String metricType) {
        if (metricType.equals(CommonConstant.DATABASE_METRIC_REGISTRY)) {
            metricRegistry.registryMetric(new DataBaseMetricRegistry(), CommonConstant.DATABASE_METRIC_REGISTRY);
        } else if (metricType.equals(CommonConstant.TIMED_METRIC_REGISTRY)) {
            metricRegistry.registryMetric(new TimedMetricRegistry(), CommonConstant.TIMED_METRIC_REGISTRY);
        } else if (metricType.equals(CommonConstant.GAUGE_REGISTRY)) {
            metricRegistry.registryMetric(new GaugeRegistry(), CommonConstant.GAUGE_REGISTRY);
        }
    }

}
