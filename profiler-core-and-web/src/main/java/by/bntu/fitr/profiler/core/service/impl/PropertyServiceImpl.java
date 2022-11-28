package by.bntu.fitr.profiler.core.service.impl;


import by.bntu.fitr.profiler.core.constant.ExceptionConstant;
import by.bntu.fitr.profiler.core.exception.handler.ProfilerCoreExceptionHandler;
import by.bntu.fitr.profiler.core.service.PropertyService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyServiceImpl implements PropertyService {
    private final ProfilerCoreExceptionHandler profilerCoreExceptionHandler = ProfilerCoreExceptionHandler.getInstance();
    private Properties properties;

    public void loadProperties(final String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            profilerCoreExceptionHandler.handle(ExceptionConstant.CANT_LOAD_PROPERTY_FILE_EXCEPTION);
        }
    }

    @Override
    public String getProperty(final String key) {
        return getIfPropertyExist(key);
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    private String getIfPropertyExist(final String propertyAttrName) {
        profilerCoreExceptionHandler.handle(propertyAttrName == null, true,
                ExceptionConstant.ATTRIBUTE_NOT_FOUND_EXCEPTION);
        String property = getProperties().getProperty(propertyAttrName);
        profilerCoreExceptionHandler.handle(property == null, true,
                ExceptionConstant.ATTRIBUTE_NOT_FOUND_EXCEPTION + " " + propertyAttrName );
        return property;
    }


}
