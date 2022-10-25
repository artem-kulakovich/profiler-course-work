package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.exception.IncorrectValueInPropertyFileException;
import by.bntu.fitr.core.exception.ProfilerPropertyNofFoundException;
import by.bntu.fitr.core.service.PropertyService;
import by.bntu.fitr.core.util.CommonUtil;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class InitializeContextsFacade implements PreRequestFacade {
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final PropertyService propertyService;
    private final ClassLoader classLoader;

    public InitializeContextsFacade(final PropertyService propertyService,
                                    final ClassLoader classLoader) {
        this.propertyService = propertyService;
        this.classLoader = classLoader;
    }

    @Override
    public void loadOnStartUp() {
        URL rootPath = classLoader.getResource(CommonConstant.PROPERTY_FILE_NAME);

        if (rootPath == null) {
            throw new ProfilerPropertyNofFoundException(ExceptionConstant.PROPERTY_FILE_NOF_FOUND_EXCEPTION);
        }

        propertyService.loadProperties(rootPath.getPath());

        List<String> whiteListValues = Arrays.asList("true", "false");
        String isDatabaseMetricShouldBeCollected = propertyService.getProperty(CommonConstant.PROFILER_METRICS_COLLECT_DATABASE);

        if (!CommonUtil.isValueFromPropertiesCorrected(isDatabaseMetricShouldBeCollected, whiteListValues)) {
            throw new IncorrectValueInPropertyFileException(ExceptionConstant.INCORRECT_VALUE_IN_PROPERTY_FILE_EXCEPTION);
        }

        propertyContext.setProperties(propertyService.getProperties());
        propertyContext.setPackageForScan(propertyService.getProperty(CommonConstant.PROFILER_SCAN_PACKAGES_ATTR));
        propertyContext.setDataBaseMetricShouldBeCollected(Boolean.parseBoolean(isDatabaseMetricShouldBeCollected));
        propertyContext.setDataBaseHost(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_HOST));
        propertyContext.setDataBaseUser(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_USER));
        propertyContext.setDataBasePassword(propertyService.getProperty(CommonConstant.PROFILER_DATABASE_CONNECTION_PASSWORD));

        applicationContext.setClassLoader(classLoader);
    }

}
