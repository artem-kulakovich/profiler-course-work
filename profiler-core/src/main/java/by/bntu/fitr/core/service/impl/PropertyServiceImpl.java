package by.bntu.fitr.core.service.impl;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.exception.CantLoadProfilerPropertyException;
import by.bntu.fitr.core.exception.PropertyAttributeNotFoundException;
import by.bntu.fitr.core.service.PropertyService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyServiceImpl implements PropertyService {
    private Properties properties;

    public void loadProperties(final String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new CantLoadProfilerPropertyException(ExceptionConstant.CANT_LOAD_PROPERTY_FILE_EXCEPTION);
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
        if (propertyAttrName == null) {
            throw new PropertyAttributeNotFoundException(ExceptionConstant.ATTRIBUTE_NOT_FOUND_EXCEPTION);
        }

        String property = getProperties().getProperty(propertyAttrName);

        if (property == null) {
            throw new PropertyAttributeNotFoundException(ExceptionConstant.ATTRIBUTE_NOT_FOUND_EXCEPTION + " "
                    + propertyAttrName);
        }

        return property;
    }


}
