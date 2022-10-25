package by.bntu.fitr.core.service;

import java.util.Properties;

public interface PropertyService {

    void loadProperties(final String path);

    String getProperty(final String key);

    Properties getProperties();

    void setProperties(final Properties properties);
}
